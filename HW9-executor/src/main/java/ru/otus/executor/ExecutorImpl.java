package ru.otus.executor;

import ru.otus.model.DataSet;

import javax.persistence.Table;
import java.math.BigInteger;

public class ExecutorImpl implements Executor {

    @Override
    public <T extends DataSet> void save(T user) {
        Class<? extends DataSet> clazz = user.getClass();

        clazz.getAnnotation(Table.class).name();



    }

    @Override
    public <T extends DataSet> T load(BigInteger id, Class<T> clazz) {
        return null;
    }
}
