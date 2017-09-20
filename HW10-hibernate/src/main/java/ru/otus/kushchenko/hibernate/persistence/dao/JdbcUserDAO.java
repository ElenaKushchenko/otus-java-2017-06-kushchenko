package ru.otus.kushchenko.hibernate.persistence.dao;

import ru.otus.kushchenko.hibernate.persistence.executor.Executor;
import ru.otus.kushchenko.hibernate.persistence.executor.ExecutorImpl;
import ru.otus.kushchenko.hibernate.model.UserDataSet;

import java.sql.Connection;

public class JdbcUserDAO implements UserDAO {
    private final Executor executor;


    public JdbcUserDAO(Connection connection) {
        executor = new ExecutorImpl(connection);
    }


    @Override
    public long save(UserDataSet user) {
        return executor.save(user);
    }

    @Override
    public UserDataSet get(long id) {
        return executor.load(id, UserDataSet.class);
    }
}
