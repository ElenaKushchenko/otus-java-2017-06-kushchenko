package ru.otus.kushchenko.hibernate.executor;

import ru.otus.kushchenko.hibernate.model.DataSet;

public interface Executor {

    <T extends DataSet> long save(T entity);

    <T extends DataSet> T load(long id, Class<T> entityClass);
}
