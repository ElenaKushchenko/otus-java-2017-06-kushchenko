package ru.otus.model.dao;

import ru.otus.model.UserDataSet;

public interface UserDAO {

    void save(UserDataSet user);

    UserDataSet load(long id);
}
