package ru.otus.kushchenko.ms.messageSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.kushchenko.ms.messageSystem.addressee.Addressee;

@Getter
@AllArgsConstructor
public abstract class Message {
    private final Address from;
    private final Address to;


    public abstract void exec(Addressee addressee);
}
