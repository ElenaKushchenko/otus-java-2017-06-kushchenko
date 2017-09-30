package ru.otus.kushchenko.ms.messageSystem.message;

import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.Message;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.messageSystem.addressee.Addressee;

public abstract class MsgToDB extends Message {

    public MsgToDB(Address from, Address to) {
        super(from, to);
    }


    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof AddressedDBService) {
            exec((AddressedDBService) addressee);
        } else {
            throw new RuntimeException("");
        }
    }

    public abstract void exec(AddressedDBService dbService);
}
