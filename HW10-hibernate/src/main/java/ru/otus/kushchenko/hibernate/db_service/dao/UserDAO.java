package ru.otus.kushchenko.hibernate.db_service.dao;

import ru.otus.kushchenko.hibernate.model.UserDataSet;

public interface UserDAO {

    long save(UserDataSet user);

    UserDataSet load(long id);
}
