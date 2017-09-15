package ru.otus.kushchenko.jetty.persistence.dao;

import ru.otus.kushchenko.jetty.model.UserDataSet;

import java.util.List;

public interface UserDAO {

    long save(UserDataSet user);

    UserDataSet load(long id);

    UserDataSet loadByName(String name);

    List<UserDataSet> loadAll();
}
