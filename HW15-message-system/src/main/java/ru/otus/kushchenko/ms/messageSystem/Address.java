package ru.otus.kushchenko.ms.messageSystem;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.concurrent.atomic.AtomicInteger;

@EqualsAndHashCode
@AllArgsConstructor
public final class Address {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger();
    private final String id;


    public Address(){
        id = String.valueOf(ID_GENERATOR.getAndIncrement());
    }
}
