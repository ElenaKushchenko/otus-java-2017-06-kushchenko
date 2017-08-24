package ru.otus.executor;

import ru.otus.model.DataSet;

import java.math.BigInteger;

public interface Executor {

    <T extends DataSet> void save(T user);

    <T extends DataSet> T load(BigInteger id, Class<T> clazz);
}
