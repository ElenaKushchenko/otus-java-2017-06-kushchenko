package ru.otus.test;

import ru.otus.testframework.annotations.After;
import ru.otus.testframework.annotations.Before;
import ru.otus.testframework.annotations.Test;

/**
 * Created by Elena on 12.07.2017.
 */
public class MyTest {

    @Before
    public void setUp() {
        System.out.println("Set Up");
    }

    @After
    public void tearDown() {
        System.out.println("Tear Down");
    }

    @Test
    public void test1() {
        System.out.println("Test 1");
    }

    @Test
    public void test2() {
        System.out.println("Test 2");
    }

    @Test
    public void test3() {
        System.out.println("Test 3");
    }
}
