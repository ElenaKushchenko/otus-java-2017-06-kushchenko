package ru.otus.kushchenko.ms.messageSystem.message;

import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.Message;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.messageSystem.addressee.Addressee;

public abstract class MsgToFrontend extends Message {

    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }


    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof AddressedFrontendService) {
            exec((AddressedFrontendService) addressee);
        } else {
            throw new RuntimeException("");
        }
    }

    public abstract void exec(AddressedFrontendService frontendService);
}