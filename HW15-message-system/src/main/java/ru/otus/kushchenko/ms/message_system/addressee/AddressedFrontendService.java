package ru.otus.kushchenko.ms.message_system.addressee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.Message;
import ru.otus.kushchenko.ms.message_system.MessageSystemContext;
import ru.otus.kushchenko.ms.message_system.message.specific.MsgGetCacheInfo;
import ru.otus.kushchenko.ms.message_system.message.specific.MsgGetUserById;
import ru.otus.kushchenko.ms.message_system.message.specific.MsgGetUserByName;
import ru.otus.kushchenko.ms.model.UserDataSet;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class AddressedFrontendService implements AddressedService {
    private final Address address;
    private final MessageSystemContext context;
    private final BlockingQueue<Object> answerQueue = new LinkedBlockingQueue<>();


    @Autowired
    public AddressedFrontendService(MessageSystemContext context, Address address){
        this.context = context;
        this.address = address;
    }


    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    public CacheInfo getCacheInfo() {
        Message message = new MsgGetCacheInfo(context.getMessageSystem(), address, context.getToAddress());
        context.getMessageSystem().sendMessage(message);

        try {
            return (CacheInfo) answerQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDataSet getUserByName(String login) {
        Message message = new MsgGetUserByName(context.getMessageSystem(), address, context.getToAddress(), login);
        context.getMessageSystem().sendMessage(message);

        try {
            Object result = answerQueue.take();
            return (UserDataSet) result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDataSet getUserById(long id) {
        Message message = new MsgGetUserById(context.getMessageSystem(), address, context.getToAddress(), id);
        context.getMessageSystem().sendMessage(message);

        try {
            Object result = answerQueue.take();
            return (UserDataSet) result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void putAnswer(Object object) {
        try {
            answerQueue.put(object);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
