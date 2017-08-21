package ru.otus.json.writer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum TestEnum {
    FIRST(1, "First"),
    SECOND(2, "Second"),
    THIRD(3, "Third");

    private int intValue;
    private String stringValue;
}
