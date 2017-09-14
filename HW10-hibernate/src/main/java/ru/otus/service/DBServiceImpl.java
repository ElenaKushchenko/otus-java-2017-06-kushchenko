package ru.otus.service;

import com.zaxxer.hikari.HikariDataSource;
import ru.otus.connection.DataSource;
import ru.otus.model.UserDataSet;
import ru.otus.model.dao.jdbc.JdbcUserDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class DBServiceImpl implements DBService {
    private final HikariDataSource dataSource;


    public DBServiceImpl() {
        dataSource = DataSource.get();
    }

    public DBServiceImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void save(UserDataSet user) {
        runInTransaction(connection -> {
            JdbcUserDAO dao = new JdbcUserDAO(connection);
            dao.save(user);
            return true;
        });
    }

    @Override
    public UserDataSet load(long id) {
        return runInTransaction(connection -> {
            JdbcUserDAO dao = new JdbcUserDAO(connection);
            return dao.load(id);
        });
    }

    private <R> R runInTransaction(Function<Connection, R> function) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            R result = function.apply(connection);
            connection.commit();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        dataSource.close();
    }
}
