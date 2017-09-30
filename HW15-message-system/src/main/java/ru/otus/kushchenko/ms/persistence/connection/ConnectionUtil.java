package ru.otus.kushchenko.ms.persistence.connection;

import java.io.IOException;
import java.util.Properties;

@SuppressWarnings("Duplicates")
public class ConnectionUtil {
    private static final String PROPERTIES_FILE = "db-connection.properties";
    private static Properties properties;


    private ConnectionUtil() {
    }


    public static Properties getConnectionProperties() {
        if (properties == null) {
            properties = new Properties();

            ClassLoader classLoader = ConnectionUtil.class.getClassLoader();
            try {
                properties.load(classLoader.getResourceAsStream(PROPERTIES_FILE));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return properties;
    }
}
