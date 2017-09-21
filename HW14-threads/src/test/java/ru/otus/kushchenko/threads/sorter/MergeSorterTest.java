package ru.otus.kushchenko.threads.sorter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSorterTest {
    private final int N = 70;
    private Integer[] actual;
    private Integer[] expected;


    @BeforeEach
    void setUp() {
        actual = new Integer[N];
        expected = new Integer[N];

        for (int i = 0; i < N; i++) {
            int value = ThreadLocalRandom.current().nextInt(-100, 101);
            actual[i] = value;
            expected[i] = value;
        }
    }

    @Test
    void sort() {
        Arrays.sort(expected);
        MergeSorter.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void parallelSort() {
        Arrays.sort(expected);
        MergeSorter.parallelSort(actual, 4);

        assertArrayEquals(expected, actual);
    }

}