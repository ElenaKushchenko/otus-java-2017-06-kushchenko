package ru.otus.kushchenko.threads.sorter;

import java.util.Arrays;

public class MergeSorter {

    public static <T extends Comparable<T>> void parallelSort(T[] array, final int numThreads) {
        if (numThreads <= 1) {
            sort(array);
            return;
        }

        int median = array.length / 2;

        T[] left = Arrays.copyOfRange(array, 0, median);
        T[] right = Arrays.copyOfRange(array, median, array.length);

        Thread leftSorter = mergeSortThread(left, numThreads);
        Thread rightSorter = mergeSortThread(right, numThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        merge(left, right, array);
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array.length <= 1) {
            return;
        }

        int median = array.length / 2;

        T[] left = Arrays.copyOfRange(array, 0, median);
        T[] right = Arrays.copyOfRange(array, median, array.length);

        sort(left);
        sort(right);

        merge(left, right, array);
    }

    private static <T extends Comparable<T>> Thread mergeSortThread(T[] array, final int numThreads) {
        System.out.println("Thread");
        return new Thread(() -> parallelSort(array, numThreads / 2));
    }

    private static <T extends Comparable<T>> void merge(T[] left, T[] right, T[] result) {
        int leftIx = 0;
        int rightIx = 0;
        int resIx = 0;

        while (leftIx < left.length && rightIx < right.length) {
            if (left[leftIx].compareTo(right[rightIx]) < 0) {
                result[resIx++] = left[leftIx++];
            } else {
                result[resIx++] = right[rightIx++];
            }
        }

        while (leftIx < left.length) {
            result[resIx++] = left[leftIx++];
        }

        while (rightIx < right.length) {
            result[resIx++] = right[rightIx++];
        }
    }
}
