package ru.otus.kushchenko.cache.db_service;

import ru.otus.kushchenko.cache.cache_engine.CacheElement;
import ru.otus.kushchenko.cache.cache_engine.CacheEngine;
import ru.otus.kushchenko.cache.cache_engine.CacheEngineImpl;
import ru.otus.kushchenko.cache.model.UserDataSet;

public class CachedDBService implements DBService {
    private final DBService dbService;
    private final CacheEngine<Long, UserDataSet> cache;


    public CachedDBService(DBService dbService) {
        this.dbService = dbService;
        cache = new CacheEngineImpl<>(100, 5000, 5000);
    }

    public CachedDBService(DBService dbService, int maxElements, long lifeTimeMs, long idleTimeMs) {
        this.dbService = dbService;
        cache = new CacheEngineImpl<>(maxElements, lifeTimeMs, idleTimeMs);
    }


    @Override
    public long save(UserDataSet user) {
        long id = dbService.save(user);
        cache.put(new CacheElement<>(id, new UserDataSet(id, user)));

        return id;
    }

    @Override
    public UserDataSet load(long id) {
        CacheElement<Long, UserDataSet> cachedUser = cache.get(id);
        if (cachedUser != null) {
            System.out.println("Cache hit: " + id);
            return cachedUser.getValue();
        }
        System.out.println("Cache miss: " + id);

        UserDataSet loadedUser = dbService.load(id);
        if (loadedUser != null) {
            cache.put(new CacheElement<>(loadedUser.getId(), loadedUser));
        }

        return loadedUser;
    }

    @Override
    public void close() throws Exception {
        dbService.close();
        cache.dispose();
    }
}
