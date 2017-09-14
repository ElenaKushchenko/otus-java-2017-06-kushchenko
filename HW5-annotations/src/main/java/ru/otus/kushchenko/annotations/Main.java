package ru.otus.kushchenko.annotations;

import ru.otus.kushchenko.annotations.test.MyTest;
import ru.otus.kushchenko.annotations.testframework.TestRunner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Elena on 12.07.2017.
 */
public class Main {

    public static void main(String... args) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        TestRunner.run(MyTest.class);
        TestRunner.run("ru.otus.kushchenko.annotations");
    }
}
