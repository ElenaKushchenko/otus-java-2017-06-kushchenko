package ru.otus.connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    public static Connection getConnection() {
        Properties properties = new Properties();

        try {
            properties.load(ConnectionManager.class.getClassLoader().getResourceAsStream("db-connection.properties"));
            Class.forName(properties.getProperty("driverName"));

            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}