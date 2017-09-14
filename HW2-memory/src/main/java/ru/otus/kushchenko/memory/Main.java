package ru.otus.kushchenko.memory;

/**
 * Created by Elena on 14.06.2017.
 */

import java.util.Arrays;

/**
 * VM options -Xmx512m -Xms512m -XX:-UseTLAB
 */
public class Main {
    private static final Runtime runtime = Runtime.getRuntime ();

    public static void main(String... args) throws InterruptedException {
        final Factory factory = new Factory();

        for (Integer count : Arrays.asList(10_000, 100, 1)) {
            System.out.println("===" + count + "===");
            calculateMemory(factory, Type.STRING, count);
            calculateMemory(factory, Type.OBJECT, count);
            calculateMemory(factory, Type.BOOLEAN, count);
            calculateMemory(factory, Type.INT, count);
            calculateMemory(factory, Type.DOUBLE, count);
            calculateMemory(factory, Type.EMPTY_ARRAY, count);
            System.out.println();
        }
    }

    private static void calculateMemory(Factory factory, Type type, int count) throws InterruptedException {
        Object[] array = new Object[count];

        runGC();
        long memory = getUsedMemory();

        for (int i = 0; i < count; i++) {
            array[i] = factory.create(type);
        }

        runGC();
        System.out.println(type + " : " + (getUsedMemory() - memory) / count);
    }

    private static void runGC() throws InterruptedException {
        System.gc();
        Thread.sleep(1000);
    }

    private static long getUsedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
