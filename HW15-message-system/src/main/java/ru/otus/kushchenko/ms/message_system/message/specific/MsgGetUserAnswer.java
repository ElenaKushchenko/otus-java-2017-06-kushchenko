package ru.otus.kushchenko.ms.message_system.message.specific;

import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.message_system.message.MsgToFrontend;
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
