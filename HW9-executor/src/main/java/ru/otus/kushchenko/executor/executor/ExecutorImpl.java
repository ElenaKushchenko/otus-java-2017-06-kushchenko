package ru.otus.kushchenko.executor.executor;

import lombok.NonNull;
import ru.otus.kushchenko.executor.connection.ConnectionManager;
import ru.otus.kushchenko.executor.model.DataSet;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("Duplicates")
public class ExecutorImpl implements Executor {

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

        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);

            int i = 1;
            for (Map.Entry<String, Field> item : columnFields.entrySet()) {
                Field field = item.getValue();
                field.setAccessible(true);
                statement.setObject(i, field.get(entity));

                i++;
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends DataSet> T load(long id, @NonNull Class<T> entityClass) {
        LinkedHashMap<String, Field> columnFields = getColumnFields(entityClass);

        String tableName = entityClass.getAnnotation(Table.class).name();
        String query = "select * from \"" + tableName + "\" where id = ?";

        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            result.next();

            T entity = entityClass.newInstance();
            for (Map.Entry<String, Field> item : columnFields.entrySet()) {
                Field field = item.getValue();
                field.setAccessible(true);
                field.set(entity, result.getObject(item.getKey()));
            }

            result.close();
            statement.close();

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
