package ru.otus.kushchenko.ms.message_system.message.specific;

import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.message_system.message.MsgToFrontend;

public class MsgGetCacheInfoAnswer extends MsgToFrontend {
    private final CacheInfo cacheInfo;

    public MsgGetCacheInfoAnswer(Address from, Address to, CacheInfo cacheInfo) {
        super(from, to);
        this.cacheInfo = cacheInfo;
    }

    @Override
    public void exec(AddressedFrontendService frontendService) {
        frontendService.putAnswer(cacheInfo);
    }
}