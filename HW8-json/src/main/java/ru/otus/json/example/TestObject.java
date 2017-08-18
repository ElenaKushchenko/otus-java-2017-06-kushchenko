package ru.otus.json.example;


public interface TestObject {
    int testObjectInterfaceIntValue = 0;

    default void testMethod() {
//        System.out.println(testObjectInterfaceIntValue);
    }
}
