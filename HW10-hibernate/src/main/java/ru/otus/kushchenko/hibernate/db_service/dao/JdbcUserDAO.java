package ru.otus.kushchenko.hibernate.db_service.dao;

import lombok.AllArgsConstructor;
import ru.otus.kushchenko.hibernate.executor.Executor;
import ru.otus.kushchenko.hibernate.executor.ExecutorImpl;
import ru.otus.kushchenko.hibernate.model.UserDataSet;

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
