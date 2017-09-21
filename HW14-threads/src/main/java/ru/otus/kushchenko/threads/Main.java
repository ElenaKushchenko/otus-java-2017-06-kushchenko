package ru.otus.kushchenko.threads;

import ru.otus.kushchenko.threads.sorter.MergeSorter;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String... args) {
        int n = 71;
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(-100, 101);
        }

        MergeSorter.parallelSort(arr, 4);

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}
