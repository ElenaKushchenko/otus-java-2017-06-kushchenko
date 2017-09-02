package ru.otus;

import ru.otus.executor.Executor;
import ru.otus.executor.ExecutorImpl;
import ru.otus.model.UserDataSet;

public class Main {

    public static void main(String... args) {
        Executor executor = new ExecutorImpl();

        UserDataSet user = executor.load(1L, UserDataSet.class);
        executor.save(user);
    }
}
