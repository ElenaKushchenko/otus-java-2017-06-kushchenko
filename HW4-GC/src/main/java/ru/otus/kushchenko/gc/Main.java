package ru.otus.kushchenko.gc;

/**
 * Created by Elena on 06.07.2017.
 */
public class Main {

    public static void main(String... args) throws InterruptedException {
        final GarbageCollectionInspector inspector = new GarbageCollectionInspector();
        final Benchmark benchmark = new Benchmark(5000);

        inspector.run();
        benchmark.run();
    }
}