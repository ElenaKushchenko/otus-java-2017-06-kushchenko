package ru.otus.kushchenko.hibernate.persistence;

import com.zaxxer.hikari.HikariDataSource;
import ru.otus.kushchenko.hibernate.persistence.connection.DataSource;
import ru.otus.kushchenko.hibernate.model.UserDataSet;
import ru.otus.kushchenko.hibernate.persistence.dao.JdbcUserDAO;

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
    public long save(UserDataSet user) {
        return runInTransaction(connection -> {
            JdbcUserDAO dao = new JdbcUserDAO(connection);
            return dao.save(user);
        });
    }

    @Override
    public UserDataSet load(long id) {
        return runInTransaction(connection -> {
            JdbcUserDAO dao = new JdbcUserDAO(connection);
            return dao.load(id);
        });
    }

    @SuppressWarnings("Duplicates")
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
