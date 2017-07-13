package ru.otus;

import java.util.ArrayList;

/**
 * Created by Elena on 06.07.2017.
 */
public class Benchmark {
    private int size = 1000;


    public Benchmark() {
    }

    public Benchmark(int size) {
        this.size = size;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @SuppressWarnings("InfiniteLoopStatement")
    void run() throws InterruptedException {
        final ArrayList<String> list = new ArrayList<>(this.size);

        while (true) {
            for (int i = 0; i < this.size; i++) {
                list.add(new String(new char[0]));
            }
            for (int i = 0; i < this.size / 2; i++) {
                list.remove(i);
            }

            System.out.println("List size: " + list.size());
            Thread.sleep(100);
        }
    }
}
