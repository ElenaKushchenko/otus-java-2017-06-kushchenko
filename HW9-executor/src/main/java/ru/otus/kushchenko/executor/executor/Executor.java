package ru.otus.kushchenko.executor.executor;

import ru.otus.kushchenko.executor.model.DataSet;

public interface Executor {

    <T extends DataSet> void save(T entity);

    <T extends DataSet> T load(long id, Class<T> entityClass);
}
