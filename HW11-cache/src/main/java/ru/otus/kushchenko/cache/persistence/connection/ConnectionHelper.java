package ru.otus.kushchenko.cache.persistence.connection;

import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class ConnectionHelper {
    private static final String PROPERTIES_FILE = "db-connection.properties";
    private static Properties properties;


    private ConnectionHelper() {
    }


    public static Properties getConnectionProperties() {
        if (properties == null) {
            properties = new Properties();

            ClassLoader classLoader = ConnectionHelper.class.getClassLoader();
            try {
                properties.load(classLoader.getResourceAsStream(PROPERTIES_FILE));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return properties;
    }
}
