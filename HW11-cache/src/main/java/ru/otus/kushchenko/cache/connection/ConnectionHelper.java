package ru.otus.kushchenko.cache.connection;

import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class ConnectionHelper {
    private static Properties properties;


    private ConnectionHelper() {
    }


    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();

            try {
                properties.load(ConnectionHelper.class.getClassLoader().getResourceAsStream("db-connection.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return properties;
    }
}
