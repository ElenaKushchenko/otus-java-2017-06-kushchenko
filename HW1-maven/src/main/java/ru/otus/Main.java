package ru.otus;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by Elena on 06.06.2017.
 */
public class Main {

    public static void main(String[] args) {
        List<String> emptyList = ImmutableList.of("Hello World", "Hallo Welt", "Bonjour tout le monde");
        emptyList.forEach(System.out::println);
    }
}
