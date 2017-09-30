package ru.otus.kushchenko.ms.message_system.message;

import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.Message;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.message_system.addressee.Addressee;

public abstract class MsgToFrontend extends Message {

    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }


    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof AddressedFrontendService) {
            exec((AddressedFrontendService) addressee);
        } else {
            throw new RuntimeException("Incorrect Addressee");
        }
    }

    public abstract void exec(AddressedFrontendService frontendService);
}