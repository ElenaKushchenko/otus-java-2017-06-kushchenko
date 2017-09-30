package ru.otus.kushchenko.ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.MessageSystem;
import ru.otus.kushchenko.ms.message_system.MessageSystemContext;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.persistence.CachedDBService;
import ru.otus.kushchenko.ms.persistence.HibernateDBService;

@Configuration
public class AppContext {
    private final Address frontAddress = new Address("frontend");
    private final Address dbAddress = new Address("db");


    @Bean
    public CachedDBService dbService() {
        return new CachedDBService(new HibernateDBService());
    }

    @Bean
    public MessageSystem messageSystem() {
        return new MessageSystem();
    }

    @Bean
    public MessageSystemContext messageSystemContext() {
        MessageSystemContext context = new MessageSystemContext(messageSystem());

        context.setFromAddress(frontAddress);
        context.setToAddress(dbAddress);

        return context;
    }

    @Bean
    public AddressedFrontendService frontendService() {
        AddressedFrontendService service = new AddressedFrontendService(messageSystemContext(), frontAddress);
        service.init();

        return service;
    }

    @Bean
    public AddressedDBService addressedDBService() {
        AddressedDBService service = new AddressedDBService(messageSystemContext(), dbService(), dbAddress);
        service.init();

        return service;
    }

    @Bean
    public AppListener appListener() {
        return new AppListener();
    }
}
