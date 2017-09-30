package ru.otus.kushchenko.ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.MessageSystem;
import ru.otus.kushchenko.ms.messageSystem.MessageSystemContext;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.persistence.CachedDBService;
import ru.otus.kushchenko.ms.persistence.HibernateDBService;

@Configuration
public class AppContext {

    @Bean
    public CachedDBService dbService() {
        return new CachedDBService(new HibernateDBService());
    }

    @Bean
    public MessageSystem messageSystem() {
        MessageSystem ms = new MessageSystem();
        ms.start();

        return ms;
    }

    @Bean
    public MessageSystemContext messageSystemContext() {
        MessageSystemContext context = new MessageSystemContext(messageSystem());

        Address frontAddress = new Address("frontend");
        context.setFromAddress(frontAddress);
        Address dbAddress = new Address("db");
        context.setToAddress(dbAddress);

        return context;
    }

    @Bean
    public AddressedFrontendService frontendService() {
        AddressedFrontendService service = new AddressedFrontendService(messageSystemContext());
        service.init();

        return service;
    }

    @Bean
    public AddressedDBService addressedDBService() {
        AddressedDBService service = new AddressedDBService(messageSystemContext(), dbService());
        service.init();

        return service;
    }
}
