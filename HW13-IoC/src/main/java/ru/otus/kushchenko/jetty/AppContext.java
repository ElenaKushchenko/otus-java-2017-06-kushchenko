package ru.otus.kushchenko.jetty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kushchenko.jetty.persistence.CachedDBService;
import ru.otus.kushchenko.jetty.persistence.DBService;
import ru.otus.kushchenko.jetty.persistence.HibernateDBService;

@Configuration
public class AppContext {

    @Bean
    public DBService dbService() {
        return new CachedDBService(new HibernateDBService());
    }
}
