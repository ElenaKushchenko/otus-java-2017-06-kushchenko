package ru.otus.kushchenko.ioc.persistence;

import org.springframework.stereotype.Component;
import ru.otus.kushchenko.ioc.cache.CacheEngine;
import ru.otus.kushchenko.ioc.cache.CacheEngineImpl;
import ru.otus.kushchenko.ioc.cache.CacheInfo;
import ru.otus.kushchenko.ioc.model.UserDataSet;

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
        cache.put(id, UserDataSet.of(id, user));

        return id;
    }

    @Override
    public UserDataSet get(long id) {
        UserDataSet cachedUser = cache.get(id);
        if (cachedUser != null) {
            return cachedUser;
        }

        UserDataSet loadedUser = dbService.get(id);
        if (loadedUser != null) {
            cache.put(loadedUser.getId(), loadedUser);
        }

        return loadedUser;
    }

    @Override
    public UserDataSet getByName(String name) {
        UserDataSet loadedUser = dbService.getByName(name);
        if (loadedUser != null) {
            cache.put(loadedUser.getId(), loadedUser);
        }

        return loadedUser;
    }

    @Override
    public List<UserDataSet> getAll() {
        List<UserDataSet> userList = dbService.getAll();
        userList.forEach(user -> cache.put(user.getId(), user));

        return userList;
    }

    @Override
    public void close() throws Exception {
        dbService.close();
        cache.dispose();
    }

    public CacheInfo getCacheInfo() {
        return cache.getInfo();
    }
}
