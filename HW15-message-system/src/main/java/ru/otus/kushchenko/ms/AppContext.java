package ru.otus.kushchenko.ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kushchenko.ms.persistence.CachedDBService;
import ru.otus.kushchenko.ms.persistence.HibernateDBService;

@Configuration
public class AppContext {

    @Bean
    public CachedDBService dbService() {
        return new CachedDBService(new HibernateDBService());
    }
}
