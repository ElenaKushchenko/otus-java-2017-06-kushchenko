package ru.otus.executor;

import ru.otus.model.DataSet;

public interface Executor {

    <T extends DataSet> void save(T entity);

    <T extends DataSet> T load(long id, Class<T> entityClass);
}
