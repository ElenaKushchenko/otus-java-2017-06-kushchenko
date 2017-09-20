package ru.otus.kushchenko.cache.persistence;

import ru.otus.kushchenko.cache.model.UserDataSet;

public interface DBService extends AutoCloseable {

    long save(UserDataSet user);

    UserDataSet get(long id);
}
