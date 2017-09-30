package ru.otus.kushchenko.ms.messageSystem.message.specific;

import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.messageSystem.message.MsgToFrontend;
import ru.otus.kushchenko.ms.model.UserDataSet;

public class MsgGetUserAnswer extends MsgToFrontend {
    private final UserDataSet user;


    public MsgGetUserAnswer(Address from, Address to, UserDataSet user) {
        super(from, to);
        this.user = user;
    }

    @Override
    public void exec(AddressedFrontendService frontendService) {
        System.out.println("Get user");
//        frontendService.sendUser(id, name);
    }
}
