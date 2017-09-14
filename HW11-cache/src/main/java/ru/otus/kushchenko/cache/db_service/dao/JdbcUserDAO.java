package ru.otus.kushchenko.cache.db_service.dao;

import lombok.AllArgsConstructor;
import ru.otus.kushchenko.cache.executor.Executor;
import ru.otus.kushchenko.cache.executor.ExecutorImpl;
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
    public UserDataSet load(long id) {
        return executor.load(id, UserDataSet.class);
    }
}
