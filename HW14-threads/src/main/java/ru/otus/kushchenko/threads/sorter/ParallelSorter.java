package ru.otus.kushchenko.threads.sorter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelSorter {

    public <T extends Comparable<T>> List<T> sort(List<T> array, final int threadNum) {
        if (threadNum < 1) {
            throw new IllegalArgumentException("Threads number must be positive");
        }

        List<ArrayPart<T>> arrayParts = splitArray(array, threadNum);

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        for (List<T> part : arrayParts) {
            executor.execute(new Thread(() -> Collections.sort(part)));
        }
        executor.shutdown();

        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return merge(arrayParts);
    }

    private <T extends Comparable<T>> List<ArrayPart<T>> splitArray(List<T> array, final int partNum) {
        final List<ArrayPart<T>> result = new ArrayList<>();

        int from = 0;
        int size = array.size();
        int num = partNum;
        for (int i = 0; i < partNum; i++) {
            int count = size / num;
            result.add(new ArrayPart<>(array.subList(from, from + count)));
            from += count;
            size -= count;
            num--;
        }

        return result;
    }

    private <T extends Comparable<T>> List<T> merge(List<ArrayPart<T>> arrayParts) {
        List<T> result = new ArrayList<>();

        int size = arrayParts.stream().mapToInt(List::size).sum();

        for (int i = 0; i < size; i++) {
            ArrayPart<T> minArrayPart = null;

            for (ArrayPart<T> arrayPart : arrayParts) {
                if (arrayPart == null || arrayPart.isEmpty()) {
                    continue;
                }

                if (minArrayPart == null) {
                    minArrayPart = arrayPart;
                } else if (arrayPart.current().compareTo(minArrayPart.current()) < 0) {
                    minArrayPart = arrayPart;
                }
            }

            result.add(minArrayPart.pop());
        }

        return result;
    }


    private class ArrayPart<E> extends ArrayList<E> {
        private int pointer = 0;


        ArrayPart(Collection<? extends E> c) {
            super(c);
        }


        @Override
        public boolean isEmpty() {
            return pointer == size();
        }

        E current() {
            return get(pointer);
        }

        E pop() {
            return get(pointer++);
        }
    }
}
