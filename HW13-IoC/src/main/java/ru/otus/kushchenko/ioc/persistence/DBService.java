package ru.otus.kushchenko.ioc.persistence;

import ru.otus.kushchenko.ioc.model.UserDataSet;

import java.util.List;

public interface DBService extends AutoCloseable {

    long save(UserDataSet user);

    UserDataSet get(long id);

    UserDataSet getByName(String name);

    List<UserDataSet> getAll();
}
