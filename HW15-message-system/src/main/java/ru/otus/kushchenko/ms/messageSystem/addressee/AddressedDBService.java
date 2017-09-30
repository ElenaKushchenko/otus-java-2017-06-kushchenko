package ru.otus.kushchenko.ms.messageSystem.addressee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.MessageSystemContext;
import ru.otus.kushchenko.ms.model.UserDataSet;
import ru.otus.kushchenko.ms.persistence.CachedDBService;

@Component
public class AddressedDBService implements AddressedService {
    private final Address address = new Address("db");
    private final MessageSystemContext context;
    private final CachedDBService dbService;


    @Autowired
    public AddressedDBService(MessageSystemContext context, CachedDBService dbService){
        this.context = context;
        this.dbService = dbService;
    }


    @Override
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    public CacheInfo getCacheInfo() {
        return dbService.getCacheInfo();
    }

    public UserDataSet getUserByName(String login) {
        return dbService.getByName(login);
    }

    public UserDataSet getUserById(long id) {
        return dbService.get(id);
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
