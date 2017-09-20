package ru.otus.kushchenko.cache.persistence.dao;

import ru.otus.kushchenko.cache.model.UserDataSet;

public interface UserDAO {

    long save(UserDataSet user);

    UserDataSet get(long id);
}
