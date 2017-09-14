package ru.otus.kushchenko.hibernate.db_service.dao;

import ru.otus.kushchenko.hibernate.executor.Executor;
import ru.otus.kushchenko.hibernate.executor.ExecutorImpl;
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
    public UserDataSet load(long id) {
        return executor.load(id, UserDataSet.class);
    }
}
