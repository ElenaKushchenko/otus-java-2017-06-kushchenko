package ru.otus.kushchenko.hibernate.persistence;

import ru.otus.kushchenko.hibernate.model.UserDataSet;

public interface DBService extends AutoCloseable {

    long save(UserDataSet user);

    UserDataSet load(long id);
}
