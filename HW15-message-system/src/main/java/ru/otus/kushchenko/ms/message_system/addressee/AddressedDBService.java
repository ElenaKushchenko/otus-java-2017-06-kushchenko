package ru.otus.kushchenko.ms.message_system.addressee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.MessageSystemContext;
import ru.otus.kushchenko.ms.model.UserDataSet;
import ru.otus.kushchenko.ms.persistence.CachedDBService;

@Component
public class AddressedDBService implements AddressedService {
    private final Address address;
    private final MessageSystemContext context;
    private final CachedDBService dbService;


    @Autowired
    public AddressedDBService(MessageSystemContext context, CachedDBService dbService, Address address){
        this.context = context;
        this.dbService = dbService;
        this.address = address;
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
