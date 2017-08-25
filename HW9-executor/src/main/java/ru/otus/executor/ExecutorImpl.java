package ru.otus.executor;

import ru.otus.model.DataSet;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class ExecutorImpl implements Executor {

    @Override
    public <T extends DataSet> void save(T entity) {


    }

    @Override
    public <T extends DataSet> T load(BigInteger id, Class<T> entityClass) {
        entityClass.getAnnotation(Table.class).name();

        Set<Field> columnFields = findFieldsWithAnnotation(entityClass, Column.class);
        for (Field field : columnFields) {
            field.getAnnotationsByType();
            field.getAnnotation(Column.class).name()
        }

        return null;
    }

    private Set<Field> findFieldsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
        Set<Field> fields = new HashSet<>();

        Class<?> c = clazz;
        while (!c.equals(Object.class)) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    fields.add(field);
                }
            }
            c = c.getSuperclass();
        }

        return fields;
    }
}
