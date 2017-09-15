package ru.otus.kushchenko.jetty.persistence;

import ru.otus.kushchenko.jetty.cache.CacheElement;
import ru.otus.kushchenko.jetty.cache.CacheEngine;
import ru.otus.kushchenko.jetty.cache.CacheEngineImpl;
import ru.otus.kushchenko.jetty.cache.CacheInfo;
import ru.otus.kushchenko.jetty.model.UserDataSet;

import java.util.List;

@SuppressWarnings("Duplicates")
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
        cache.put(new CacheElement<>(id, UserDataSet.of(id, user)));

        return id;
    }

    @Override
    public UserDataSet load(long id) {
        CacheElement<Long, UserDataSet> cachedUser = cache.get(id);
        if (cachedUser != null) {
            return cachedUser.getValue();
        }

        UserDataSet loadedUser = dbService.load(id);
        if (loadedUser != null) {
            cache.put(new CacheElement<>(loadedUser.getId(), loadedUser));
        }

        return loadedUser;
    }

    @Override
    public UserDataSet loadByName(String name) {
        UserDataSet loadedUser = dbService.loadByName(name);
        if (loadedUser != null) {
            cache.put(new CacheElement<>(loadedUser.getId(), loadedUser));
        }

        return loadedUser;
    }

    @Override
    public List<UserDataSet> loadAll() {
        List<UserDataSet> userList = dbService.loadAll();
        userList.forEach(user -> cache.put(new CacheElement<>(user.getId(), user)));    //ToDO

        return userList;
    }

    @Override
    public void close() throws Exception {
        dbService.close();
        cache.dispose();
    }

    public CacheInfo getCacheInfo() {   //FixMe
        return cache.getInfo();
    }
}
