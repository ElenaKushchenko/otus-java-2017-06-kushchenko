package ru.otus.kushchenko.ms.messageSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSystemContext {
    private MessageSystem messageSystem;
    private Address fromAddress;
    private Address toAddress;


    @Autowired
    public MessageSystemContext(MessageSystem messageSystem){
        this.messageSystem = messageSystem;
    }


    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }
}
