package ru.otus;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Elena on 18.06.2017.
 */
public class Main {

    public static void main(String... args) {
        MyArrayList<Integer> list = new MyArrayList<>();

        System.out.println("===addAll===");

        Collections.addAll(list, 1, 2, 3, 4, 5);
        System.out.println(list);

        Collections.addAll(list, 6, 7, 8, 9, 10);
        System.out.println(list);

        System.out.println("===copy===");

        MyArrayList<Integer> source = new MyArrayList<>();
        MyArrayList<Integer> dest = new MyArrayList<>();

        Collections.addAll(source, 1, 2, 3, 4, 5);
        Collections.addAll(dest, 0, 0, 0, 0, 0);

        Collections.copy(dest, source);

        System.out.println("Source: " + source);
        System.out.println("Destination: " + dest);

        System.out.println("===sort===");

        MyArrayList<Integer> sortList = new MyArrayList<>(Arrays.asList(0, 6, 2, 4, -1));
        System.out.println("Before: " + sortList);
        Collections.sort(sortList);
        System.out.println("After: " + sortList);
    }
}
