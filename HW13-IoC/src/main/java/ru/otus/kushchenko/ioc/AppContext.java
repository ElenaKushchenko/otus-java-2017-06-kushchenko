package ru.otus.kushchenko.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kushchenko.ioc.persistence.CachedDBService;

@Configuration
public class AppContext {

    @Bean
    public CachedDBService cachedDBService() {
        return new CachedDBService();
    }
}
