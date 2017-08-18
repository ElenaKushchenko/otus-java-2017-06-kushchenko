package ru.otus.json.writer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static ru.otus.json.writer.TypeResolver.isWrapperType;


public class SimpleJsonWriter implements JsonWriter {

    public String toJson(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();

        if (clazz.equals(String.class)) {
            return toJsonString(object);
        }

        if (clazz.isPrimitive()
                || isWrapperType(object.getClass())
                || object.getClass().isEnum()) {
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
        return String.format("\"%s\"", object);
    }

    private String toJsonValue(Object object) {
        return object.toString();
    }

    private String toJsonArray(Object object) throws IllegalAccessException {
        List<String> strings = new ArrayList<>();
        int length = Array.getLength(object);

        for (int i = 0; i < length; i++) {
            Object item = Array.get(object, i);
            strings.add(toJson(item));
        }

        return String.format("[%s]", String.join(",", strings));
    }

    private String toJsonCollection(Object object) throws IllegalAccessException {
        List<String> strings = new ArrayList<>();

        for (Object item : ((Collection) object)) {
            strings.add(toJson(item));
        }

        return String.format("[%s]", String.join(",", strings));
    }

    private String toJsonMap(Object object) throws IllegalAccessException {
        Map map = (Map) object;
        Map<String, String> stringMap = new LinkedHashMap<>();

        for (Object item : map.keySet()) {
            String key = item.toString();
            Object value = map.get(item);

            stringMap.put(key, toJson(value));
        }

        return String.format("{%s}",
                stringMap.entrySet().stream()
                        .map(item -> String.format("\"%s\":%s", item.getKey(), item.getValue()))
                        .collect(Collectors.joining(","))
        );
    }

    private String toJsonObject(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();
        Map<String, String> stringMap = new LinkedHashMap<>();

        while (!clazz.equals(Object.class)) {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isTransient(field.getModifiers())) {
                    break;
                }
                field.setAccessible(true);

                String key = field.getName();
                Object value = field.get(object);

                stringMap.put(key, toJson(value));
            }

            clazz = clazz.getSuperclass();
        }

        return String.format("{%s}",
                stringMap.entrySet().stream()
                        .map(item -> String.format("\"%s\":%s", item.getKey(), item.getValue()))
                        .collect(Collectors.joining(","))
        );
    }
}
