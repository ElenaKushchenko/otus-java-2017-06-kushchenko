package ru.otus;

/**
 * Created by Elena on 14.06.2017.
 */

/**
 * VM options -Xmx512m -Xms512m
 */
public class Main {

    public static void main(String... args) throws InterruptedException {
        final Factory factory = new Factory();

        calc(factory, "String");
        calc(factory, "Object");
    }

    private static void calc(Factory factory, String type) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();

        System.gc();
        Thread.sleep(1000);

        long mem = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("=== " + type + "Array[10000] ===");
        Object[] array = new Object[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = factory.create(type);
        }
        System.out.println(runtime.totalMemory() - runtime.freeMemory() - mem);
    }
}
