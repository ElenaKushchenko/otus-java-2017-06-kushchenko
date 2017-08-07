package ru.otus.json.example;

/**
 * Created by Elena on 05.08.2017.
 */
public interface TestObject {
    int testObjectInterfaceIntValue = 0;

    default void testMethod() {
//        System.out.println(testObjectInterfaceIntValue);
    }
}
