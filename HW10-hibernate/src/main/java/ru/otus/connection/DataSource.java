package ru.otus.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

public class DataSource {
    private static HikariDataSource datasource;


    private DataSource() {
    }


    public static HikariDataSource get() {
        if (datasource == null) {
            HikariConfig config = new HikariConfig();
            Properties connectionProps = ConnectionHelper.getProperties();

            config.setJdbcUrl(connectionProps.getProperty("url"));
            config.setDriverClassName(connectionProps.getProperty("driverName"));
            config.setUsername(connectionProps.getProperty("username"));
            config.setPassword(connectionProps.getProperty("password"));

            config.setMaximumPoolSize(10);
            config.setAutoCommit(false);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "1024");

            datasource = new HikariDataSource(config);
        }

        return datasource;
    }
}
