package ru.otus.kushchenko.jetty.cache;


public interface CacheEngine<K, V> {

    void put(CacheElement<K, V> element);

    CacheElement<K, V> get(K key);

    int getHitCount();

    int getMissCount();

    CacheInfo getInfo();

    void dispose();
}
