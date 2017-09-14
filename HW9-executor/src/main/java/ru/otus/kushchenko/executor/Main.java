package ru.otus.kushchenko.executor;

import ru.otus.kushchenko.executor.executor.Executor;
import ru.otus.kushchenko.executor.executor.ExecutorImpl;
import ru.otus.kushchenko.executor.model.UserDataSet;

public class Main {

    public static void main(String... args) {
        Executor executor = new ExecutorImpl();

        UserDataSet user = executor.load(1L, UserDataSet.class);
        executor.save(user);
    }
}
