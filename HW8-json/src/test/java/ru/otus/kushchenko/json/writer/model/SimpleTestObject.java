package ru.otus.kushchenko.json.writer.model;


public class SimpleTestObject extends AbstractTestObject {
    protected int simpleInt;
    protected String simpleString;
    protected TestEnum simpleEnum;
    protected double[] simpleArray;

    public SimpleTestObject(String abstractString, int simpleInt, String simpleString, TestEnum simpleEnum, double[] simpleArray) {
        super(abstractString);
        this.simpleInt = simpleInt;
        this.simpleString = simpleString;
        this.simpleEnum = simpleEnum;
        this.simpleArray = simpleArray;
    }
}
