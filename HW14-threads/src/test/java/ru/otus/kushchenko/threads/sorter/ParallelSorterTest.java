package ru.otus.kushchenko.threads.sorter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ParallelSorterTest {
    private final int N = 71;
    private List<Integer> actual;
    private List<Integer> expected;


    @BeforeEach
    void setUp() {
        actual = Stream.generate(() -> ThreadLocalRandom.current().nextInt(-100, 101))
                .limit(N)
                .collect(Collectors.toList());
        expected = new ArrayList<>(actual);
    }

    @Test
    void sort() {
        Collections.sort(expected);
        ParallelSorter sorter = new ParallelSorter();

        Assertions.assertArrayEquals(expected.toArray(), sorter.sort(actual, 4).toArray());
    }
}