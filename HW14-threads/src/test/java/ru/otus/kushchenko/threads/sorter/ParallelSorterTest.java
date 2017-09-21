package ru.otus.kushchenko.threads.sorter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ParallelSorterTest {
    private final int N = 71;
    private List<Integer> array;


    @BeforeEach
    void setUp() {
        array = Stream.generate(() -> ThreadLocalRandom.current().nextInt(-100, 101))
                .limit(N)
                .collect(Collectors.toList());
    }

    @Test
    void sort() {
        ParallelSorter sorter = new ParallelSorter();
        Assertions.assertTrue(isSorted(sorter.sort(array, 4)));
    }

    private <T extends Comparable<T>> boolean isSorted(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }

        return true;
    }

}