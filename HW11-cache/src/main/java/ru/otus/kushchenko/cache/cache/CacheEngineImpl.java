package ru.otus.kushchenko.cache.cache;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;


public class CacheEngineImpl<K, V> implements CacheEngine<K, V> {
    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;

    private final Map<K, SoftReference<CacheElement<V>>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;


    public CacheEngineImpl() {
        this.maxElements = 100;
        this.lifeTimeMs = 5000;
        this.idleTimeMs = 5000;
    }

    public CacheEngineImpl(int maxElements, long lifeTimeMs, long idleTimeMs) {
        if (maxElements <= 0 || lifeTimeMs <= 0 || idleTimeMs <= 0) {
            throw new IllegalArgumentException("Arguments must be positive");
        }

        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs;
        this.idleTimeMs = idleTimeMs;
    }


    @Override
    public void put(K key, V value) {
        if (elements.size() == maxElements) {
            elements.remove(elements.keySet().iterator().next());
        }

        CacheElement<V> element = new CacheElement<>(value);
        elements.put(key, new SoftReference<>(element));

        checkLifeTime(key);
        checkIdleTime(key);
    }

    @Override
    public V get(K key) {
        SoftReference<CacheElement<V>> elementReference = elements.get(key);

        if (elementReference != null) {
            CacheElement<V> cacheElement = elementReference.get();
            if (cacheElement != null) {
                cacheElement.setAccessed();
                hit++;

                return cacheElement.getValue();
            }
        }

        miss++;
        return null;
    }

    @Override
    public int getHitCount() {
        return hit;
    }

    @Override
    public int getMissCount() {
        return miss;
    }

    @Override
    public void dispose() {
        timer.cancel();
    }


    private TimerTask getTimerTask(final K key, Function<CacheElement<V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<CacheElement<V>> elementReference = elements.get(key);
                if (elementReference == null || isT1BeforeT2(timeFunction.apply(elementReference.get()), System.currentTimeMillis())) {
                    elements.remove(key);
                }
            }
        };
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }

    private void checkLifeTime(K key) {
        if (lifeTimeMs != 0) {
            TimerTask lifeTimerTask = getTimerTask(key, element -> element.getCreationTime() + lifeTimeMs);
            timer.schedule(lifeTimerTask, lifeTimeMs);
        }
    }

    private void checkIdleTime(K key) {
        if (idleTimeMs != 0) {
            TimerTask idleTimerTask = getTimerTask(key, element -> element.getLastAccessTime() + idleTimeMs);
            timer.schedule(idleTimerTask, idleTimeMs);
        }
    }
}
