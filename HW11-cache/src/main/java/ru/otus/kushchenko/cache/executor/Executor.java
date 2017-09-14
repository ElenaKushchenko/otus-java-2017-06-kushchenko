package ru.otus.kushchenko.cache.executor;

import ru.otus.kushchenko.cache.model.DataSet;

public interface Executor {

    <T extends DataSet> long save(T entity);

    <T extends DataSet> T load(long id, Class<T> entityClass);
}
