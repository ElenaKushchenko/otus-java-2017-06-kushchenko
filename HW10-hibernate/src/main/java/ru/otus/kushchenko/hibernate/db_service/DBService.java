package ru.otus.kushchenko.hibernate.db_service;

import ru.otus.kushchenko.hibernate.model.UserDataSet;

public interface DBService extends AutoCloseable {

    long save(UserDataSet user);

    UserDataSet load(long id);
}
