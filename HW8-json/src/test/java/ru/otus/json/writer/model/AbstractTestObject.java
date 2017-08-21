package ru.otus.json.writer.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class AbstractTestObject implements TestObject {
    protected String abstractString = "Hello world!";
}
