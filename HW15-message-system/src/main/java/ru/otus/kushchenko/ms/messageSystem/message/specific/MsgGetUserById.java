package ru.otus.kushchenko.ms.messageSystem.message.specific;

import ru.otus.kushchenko.ms.messageSystem.Address;
import ru.otus.kushchenko.ms.messageSystem.MessageSystem;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.messageSystem.message.MsgToDB;
import ru.otus.kushchenko.ms.model.UserDataSet;

public class MsgGetUserById extends MsgToDB {
    private final MessageSystem messageSystem;
    private final long id;


    public MsgGetUserById(MessageSystem messageSystem, Address from, Address to, long id) {
        super(from, to);
        this.messageSystem = messageSystem;
        this.id = id;
    }


    @Override
    public void exec(AddressedDBService dbService) {
        UserDataSet user = dbService.getUserById(id);
        messageSystem.sendMessage(new MsgGetUserAnswer(getTo(), getFrom(), user));
    }
}
