package ru.otus.kushchenko.ms.message_system.message.specific;

import ru.otus.kushchenko.ms.message_system.Address;
import ru.otus.kushchenko.ms.message_system.MessageSystem;
import ru.otus.kushchenko.ms.message_system.addressee.AddressedDBService;
import ru.otus.kushchenko.ms.message_system.message.MsgToDB;
import ru.otus.kushchenko.ms.model.UserDataSet;

public class MsgGetUserByName extends MsgToDB {
    private final MessageSystem messageSystem;
    private final String login;


    public MsgGetUserByName(MessageSystem messageSystem, Address from, Address to, String login) {
        super(from, to);
        this.messageSystem = messageSystem;
        this.login = login;
    }


    @Override
    public void exec(AddressedDBService dbService) {
        UserDataSet user = dbService.getUserByName(login);
        messageSystem.sendMessage(new MsgGetUserAnswer(getTo(), getFrom(), user));
    }
}
