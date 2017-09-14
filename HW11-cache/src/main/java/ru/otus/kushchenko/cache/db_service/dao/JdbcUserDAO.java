package ru.otus.kushchenko.cache.db_service.dao;

import lombok.AllArgsConstructor;
import ru.otus.kushchenko.cache.executor.Executor;
import ru.otus.kushchenko.cache.executor.ExecutorImpl;
import ru.otus.kushchenko.cache.model.UserDataSet;

import java.sql.Connection;

@AllArgsConstructor

public class JdbcUserDAO implements UserDAO {
    private Connection connection;


    @Override
    public long save(UserDataSet user) {
        Executor executor = new ExecutorImpl(connection);
        return executor.save(user);
    }

    @Override
    public UserDataSet load(long id) {
        Executor executor = new ExecutorImpl(connection);
        return executor.load(id, UserDataSet.class);
    }
}
