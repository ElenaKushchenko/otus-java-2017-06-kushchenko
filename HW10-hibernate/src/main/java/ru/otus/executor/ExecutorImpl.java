package ru.otus.executor;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.otus.model.DataSet;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor

public class ExecutorImpl implements Executor {
    private final Connection connection;

    @Override
    public <T extends DataSet> void save(@NonNull T entity) {
        LinkedHashMap<String, Field> columnFields = getColumnFields(entity.getClass());
        columnFields.remove("id");

        String statementParams = String.format("(%s)",
                Stream.generate(() -> "?")
                        .limit(columnFields.size())
                        .collect(Collectors.joining(", "))
        );

        String tableName = entity.getClass().getAnnotation(Table.class).name();
        String query = "insert into \"" + tableName + "\""
                + String.format(" (%s) ", String.join(", ", columnFields.keySet()))
                + " values " + statementParams;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int i = 1;
            for (Map.Entry<String, Field> item : columnFields.entrySet()) {
                Field field = item.getValue();
                field.setAccessible(true);
                statement.setObject(i, field.get(entity));

                i++;
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends DataSet> T load(long id, @NonNull Class<T> entityClass) {
        LinkedHashMap<String, Field> columnFields = getColumnFields(entityClass);

        String tableName = entityClass.getAnnotation(Table.class).name();
        String query = "select * from " + tableName + " where id = " + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            result.next();

            T entity = entityClass.newInstance();
            for (Map.Entry<String, Field> item : columnFields.entrySet()) {
                Field field = item.getValue();
                field.setAccessible(true);
                field.set(entity, result.getObject(item.getKey()));
            }

            return entity;
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private LinkedHashMap<String, Field> getColumnFields(Class<?> clazz) {
        LinkedHashMap<String, Field> fields = new LinkedHashMap<>();

        Class<?> c = clazz;
        while (!c.equals(Object.class)) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    fields.put(field.getAnnotation(Column.class).name(), field);
                }
            }
            c = c.getSuperclass();
        }

        return fields;
    }
}
