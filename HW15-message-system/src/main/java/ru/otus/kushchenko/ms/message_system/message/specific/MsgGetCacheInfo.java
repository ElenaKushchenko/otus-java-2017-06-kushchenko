package ru.otus.kushchenko.ms.message_system.message.specific;

import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.MessageSystem;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.message_system.message.MsgToDB;

public class MsgGetCacheInfo extends MsgToDB {
    private final MessageSystem messageSystem;


    public MsgGetCacheInfo(MessageSystem messageSystem, Address from, Address to) {
        super(from, to);
        this.messageSystem = messageSystem;
    }


    @Override
    public void exec(AddressedDBService dbService) {
        CacheInfo cacheInfo = dbService.getCacheInfo();
        messageSystem.sendMessage(new MsgGetCacheInfoAnswer(getTo(), getFrom(), cacheInfo));
    }
}
