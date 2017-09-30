package ru.otus.kushchenko.ms.messageSystem.message.specific;

import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.messageSystem.message.MsgToFrontend;

public class MsgGetCacheInfoAnswer extends MsgToFrontend {
    private final CacheInfo cacheInfo;

    public MsgGetCacheInfoAnswer(Address from, Address to, CacheInfo cacheInfo) {
        super(from, to);
        this.cacheInfo = cacheInfo;
    }

    @Override
    public void exec(AddressedFrontendService frontendService) {
        System.out.println("Get cache info");
//        frontendService.sendUser(id, name);
    }
}