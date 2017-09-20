package ru.otus.kushchenko.cache.persistence.dao;

import ru.otus.kushchenko.cache.persistence.executor.Executor;
import ru.otus.kushchenko.cache.persistence.executor.ExecutorImpl;
import ru.otus.kushchenko.cache.model.UserDataSet;

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
