package ru.otus.kushchenko.json.writer.model;

import java.util.List;
import java.util.Map;


public class ExtendedTestObject extends SimpleTestObject {
    private Integer extendedInteger;
    private List<String> extendedList;
    private Map<Integer, String> extendedMap;


    public ExtendedTestObject(String abstractString, int simpleInt, String simpleString, TestEnum simpleEnum,
                              double[] simpleArray, Integer extendedInteger, List<String> extendedList, Map<Integer, String> extendedMap) {
        super(abstractString, simpleInt, simpleString, simpleEnum, simpleArray);
        this.extendedInteger = extendedInteger;
        this.extendedList = extendedList;
        this.extendedMap = extendedMap;
    }
}
