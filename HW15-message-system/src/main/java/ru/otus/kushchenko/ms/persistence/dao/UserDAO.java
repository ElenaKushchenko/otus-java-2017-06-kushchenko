package ru.otus.kushchenko.ms.persistence.dao;

import ru.otus.kushchenko.ms.model.UserDataSet;

import java.util.List;

public interface UserDAO {

    long save(UserDataSet user);

    UserDataSet get(long id);

    UserDataSet getByName(String name);

    List<UserDataSet> getAll();
}
