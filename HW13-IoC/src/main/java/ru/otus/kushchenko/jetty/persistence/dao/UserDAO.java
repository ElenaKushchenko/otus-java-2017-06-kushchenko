package ru.otus.kushchenko.jetty.persistence.dao;

import ru.otus.kushchenko.jetty.model.UserDataSet;

import java.util.List;

public interface UserDAO {

    long save(UserDataSet user);

    UserDataSet get(long id);

    UserDataSet getByName(String name);

    List<UserDataSet> getAll();
}
