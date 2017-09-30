package ru.otus.kushchenko.ms.message_system.message.specific;

import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.MessageSystem;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.message_system.message.MsgToDB;
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
