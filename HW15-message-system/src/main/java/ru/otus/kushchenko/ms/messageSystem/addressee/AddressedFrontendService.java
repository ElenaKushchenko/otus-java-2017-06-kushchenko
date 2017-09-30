package ru.otus.kushchenko.ms.messageSystem.addressee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.Message;
import ru.otus.kushchenko.ms.messageSystem.MessageSystemContext;
import ru.otus.kushchenko.ms.messageSystem.message.specific.MsgGetCacheInfo;
import ru.otus.kushchenko.ms.messageSystem.message.specific.MsgGetUserById;
import ru.otus.kushchenko.ms.messageSystem.message.specific.MsgGetUserByName;
import ru.otus.kushchenko.ms.model.UserDataSet;

@Component
public class AddressedFrontendService implements AddressedService {
    private final Address address = new Address("frontend");
    private final MessageSystemContext context;


    @Autowired
    public AddressedFrontendService(MessageSystemContext context){
        this.context = context;
    }


    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    public CacheInfo getCacheInfo() {
        Message message = new MsgGetCacheInfo(context.getMessageSystem(), address, context.getFromAddress());
        context.getMessageSystem().sendMessage(message);

        return null;
    }

    public UserDataSet getUserByName(String login) {
        Message message = new MsgGetUserByName(context.getMessageSystem(), address, context.getFromAddress(), login);
        context.getMessageSystem().sendMessage(message);

        return null;
    }

    public UserDataSet getUserById(long id) {
        Message message = new MsgGetUserById(context.getMessageSystem(), address, context.getFromAddress(), id);
        context.getMessageSystem().sendMessage(message);

        return null;
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
