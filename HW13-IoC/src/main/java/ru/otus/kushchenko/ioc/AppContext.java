package ru.otus.kushchenko.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kushchenko.ioc.persistence.CachedDBService;
import ru.otus.kushchenko.ioc.persistence.HibernateDBService;

@Configuration
public class AppContext {

    @Bean
    public CachedDBService dbService() {
        return new CachedDBService(new HibernateDBService());
    }
}
