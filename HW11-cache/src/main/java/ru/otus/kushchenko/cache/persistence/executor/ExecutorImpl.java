package ru.otus.kushchenko.cache.persistence.executor;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.otus.kushchenko.cache.model.DataSet;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@AllArgsConstructor

@SuppressWarnings("Duplicates")
public class ExecutorImpl implements Executor {
    private final Connection connection;

    @Override
    public <T extends DataSet> long save(@NonNull T entity) {
        try (Statement statement = connection.createStatement()) {
            String query = generateInsertQuery(entity);
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            return result.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends DataSet> T load(long id, @NonNull Class<T> entityClass) {
        Map<String, Field> columnFieldsMap = getColumnFields(entityClass);

        String query = generateSelectByIdQuery(entityClass, id);

        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            if (!result.next()) {
                return null;
            }

            T entity = entityClass.newInstance();
            for (Map.Entry<String, Field> item : columnFieldsMap.entrySet()) {
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


    private <T extends DataSet> String generateSelectByIdQuery(@NonNull Class<T> entityClass, long id) {
        String tableName = entityClass.getAnnotation(Table.class).name();
        return "select * from \"" + tableName + "\" where id = " + id;
    }

    private <T extends DataSet> String generateInsertQuery(@NonNull T entity) {
        String tableName = entity.getClass().getAnnotation(Table.class).name();
        Map<String, Field> columnFieldsMap = getColumnFields(entity.getClass());
        columnFieldsMap.remove("id");      //Auto Incremented

        Set<String> columns = columnFieldsMap.keySet();
        List<String> values = new ArrayList<>();
        try {
            for(Map.Entry<String, Field> item : columnFieldsMap.entrySet()) {
                Field field = item.getValue();
                field.setAccessible(true);

                String value = String.valueOf(field.get(entity));
                if (String.class.equals(field.getType())) {
                    value = "'" + value + "'";
                }

                values.add(value);
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return "insert into \"" + tableName + "\""
                + String.format(" (%s) ", String.join(", ", columns))
                + " values " + String.format(" (%s) ", String.join(", ", values));
    }
}
