package ru.otus.kushchenko.cache.db_service.dao;

import ru.otus.kushchenko.cache.model.UserDataSet;

public interface UserDAO {

    long save(UserDataSet user);

    UserDataSet load(long id);
}
