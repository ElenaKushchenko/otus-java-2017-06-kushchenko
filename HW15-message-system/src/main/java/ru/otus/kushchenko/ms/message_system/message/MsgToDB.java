package ru.otus.kushchenko.ms.message_system.message;

import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.Message;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.message_system.addressee.Addressee;

public abstract class MsgToDB extends Message {

    public MsgToDB(Address from, Address to) {
        super(from, to);
    }


    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof AddressedDBService) {
            exec((AddressedDBService) addressee);
        } else {
            throw new RuntimeException("Incorrect Addressee");
        }
    }

    public abstract void exec(AddressedDBService dbService);
}
