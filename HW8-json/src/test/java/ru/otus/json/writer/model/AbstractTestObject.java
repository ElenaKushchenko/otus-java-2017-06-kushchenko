package ru.otus.json.writer.model;


public class AbstractTestObject implements TestObject {
    protected String abstractString = "Hello world!";

    protected AbstractTestObject(String abstractString) {
        this.abstractString = abstractString;
    }
}
