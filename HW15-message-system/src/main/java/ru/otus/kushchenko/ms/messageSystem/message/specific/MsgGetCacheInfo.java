package ru.otus.kushchenko.ms.messageSystem.message.specific;

import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.MessageSystem;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.messageSystem.message.MsgToDB;

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
