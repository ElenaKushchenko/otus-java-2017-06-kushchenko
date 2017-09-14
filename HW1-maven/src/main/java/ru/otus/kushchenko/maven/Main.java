package ru.otus.kushchenko.maven;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by Elena on 06.06.2017.
 */
public class Main {

    public static void main(String[] args) {
        List<String> greetingList = ImmutableList.of("Hello World", "Hallo Welt", "Bonjour tout le monde");
        greetingList.forEach(System.out::println);
    }
}
