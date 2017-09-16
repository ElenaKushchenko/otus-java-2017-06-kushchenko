package ru.otus.kushchenko.cache.persistence;

import com.zaxxer.hikari.HikariDataSource;
import ru.otus.kushchenko.cache.persistence.connection.DataSource;
import ru.otus.kushchenko.cache.model.UserDataSet;
import ru.otus.kushchenko.cache.persistence.dao.JdbcUserDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

public class JdbcDBService implements DBService {
    private final HikariDataSource dataSource;


    public JdbcDBService() {
        dataSource = DataSource.get();
    }

    public JdbcDBService(HikariDataSource dataSource) {
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
    public void close() throws Exception {
        dataSource.close();
    }
}
