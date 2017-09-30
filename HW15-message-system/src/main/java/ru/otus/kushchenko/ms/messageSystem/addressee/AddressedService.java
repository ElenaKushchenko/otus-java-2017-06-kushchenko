package ru.otus.kushchenko.ms.messageSystem.addressee;

import ru.otus.kushchenko.ms.cache.CacheInfo;
import ru.otus.kushchenko.ms.model.UserDataSet;

public interface AddressedService extends Addressee {

    void init();

    CacheInfo getCacheInfo();

    UserDataSet getUserByName(String login);

    UserDataSet getUserById(long id);
}
