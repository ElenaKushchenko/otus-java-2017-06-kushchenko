package ru.otus.json.writer.model;


public enum TestEnum {
    FIRST(1, "First"),
    SECOND(2, "Second"),
    THIRD(3, "Third");

    private int intValue;
    private String stringValue;


    TestEnum(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }
}
