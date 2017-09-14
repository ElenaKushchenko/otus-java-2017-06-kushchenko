package ru.otus.service;

import ru.otus.model.UserDataSet;

public interface DBService extends AutoCloseable {

    void save(UserDataSet user);

    UserDataSet load(long id);
}
