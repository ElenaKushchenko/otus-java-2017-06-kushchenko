package ru.otus.model.dao.jdbc;

import lombok.AllArgsConstructor;
import ru.otus.executor.Executor;
import ru.otus.executor.ExecutorImpl;
import ru.otus.model.UserDataSet;
import ru.otus.model.dao.UserDAO;

import java.sql.Connection;

@AllArgsConstructor

public class JdbcUserDAO implements UserDAO {
    private Connection connection;


    @Override
    public void save(UserDataSet user) {
        Executor executor = new ExecutorImpl(connection);
        executor.save(user);
    }

    @Override
    public UserDataSet load(long id) {
        Executor executor = new ExecutorImpl(connection);
        return executor.load(id, UserDataSet.class);
    }
}
