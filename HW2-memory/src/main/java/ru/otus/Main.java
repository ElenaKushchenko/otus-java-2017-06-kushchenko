package ru.otus;

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

        for (Integer count : Arrays.asList(10_000, 100, 1, 0)) {
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
        runGC();

        long memory = getUsedMemory();

        Object[] array = new Object[count];
        for (int i = 0; i < count; i++) {
            array[i] = factory.create(type);
        }

        System.out.print(type + " : ");
        if (count != 0) {
            System.out.println((getUsedMemory() - memory) / count);
        } else {
            System.out.println(getUsedMemory() - memory);
        }
    }

    private static void runGC() throws InterruptedException {
        System.gc();
        Thread.sleep(1000);
    }

    private static long getUsedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
