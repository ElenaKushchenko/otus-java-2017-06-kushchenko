package ru.otus.kushchenko.cache.db_service;

import ru.otus.kushchenko.cache.model.UserDataSet;

public interface DBService extends AutoCloseable {

    long save(UserDataSet user);

    UserDataSet load(long id);
}
