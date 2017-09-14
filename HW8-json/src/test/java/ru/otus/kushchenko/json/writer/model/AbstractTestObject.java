package ru.otus.kushchenko.json.writer.model;


public class AbstractTestObject implements TestObject {
    protected String abstractString = "Hello world!";

    protected AbstractTestObject(String abstractString) {
        this.abstractString = abstractString;
    }
}
