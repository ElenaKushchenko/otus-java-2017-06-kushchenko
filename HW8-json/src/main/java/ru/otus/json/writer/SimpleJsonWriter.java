package ru.otus.json.writer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import static ru.otus.json.writer.util.JsonFormatter.*;


public class SimpleJsonWriter implements JsonWriter {

    public String toJson(Object object) {
        if (object == null) {
            return null;
        }

        Class clazz = object.getClass();

        if (clazz.equals(String.class)
                || clazz.equals(Character.class)
                || clazz.isEnum()) {
            return toJsonString(object);
        }

        if (object instanceof Boolean
                || object instanceof Number) {
            return toJsonValue(object);
        }

        if (clazz.isArray()) {
            return toJsonArray(object);
        }

        if (object instanceof Collection) {
            return toJsonCollection(object);
        }

        if (object instanceof Map) {
            return toJsonMap(object);
        }

        return toJsonObject(object);
    }


    private String toJsonString(Object object) {
        return formatString(object);
    }

    private String toJsonValue(Object object) {
        return object.toString();
    }

    private String toJsonArray(Object object) {
        List<String> stringList = new ArrayList<>();
        int length = Array.getLength(object);

        for (int i = 0; i < length; i++) {
            Object item = Array.get(object, i);
            stringList.add(toJson(item));
        }

        return formatArray(stringList);
    }

    private String toJsonCollection(Object object) {
        List<String> stringList = new ArrayList<>();

        for (Object item : ((Collection) object)) {
            stringList.add(toJson(item));
        }

        return formatArray(stringList);
    }

    private String toJsonMap(Object object) {
        Map<String, String> stringMap = new LinkedHashMap<>();
        Map map = (Map) object;

        for (Object item : map.keySet()) {
            if (item == null && map.get(item) == null) {
                continue;
            }

            String key = String.valueOf(item);
            Object value = map.get(item);

            stringMap.put(key, toJson(value));
        }

        return formatObject(stringMap);
    }

    private String toJsonObject(Object object) {
        Map<String, String> stringMap = new LinkedHashMap<>();
        Class clazz = object.getClass();

        while (!clazz.equals(Object.class)) {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);

                String key = field.getName();
                Object value;

                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                if (value == null) {
                    continue;
                }

                stringMap.put(key, toJson(value));
            }

            clazz = clazz.getSuperclass();
        }

        return formatObject(stringMap);
    }
}
