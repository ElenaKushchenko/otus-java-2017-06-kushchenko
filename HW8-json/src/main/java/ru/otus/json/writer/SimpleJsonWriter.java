package ru.otus.json.writer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Elena on 04.08.2017.
 */
public class SimpleJsonWriter implements JsonWriter {
    private static final Set<Class<?>> WRAPPER_TYPES;


    static {
        WRAPPER_TYPES = new HashSet<>();

        WRAPPER_TYPES.add(Boolean.class);
        WRAPPER_TYPES.add(Character.class);
        WRAPPER_TYPES.add(Byte.class);
        WRAPPER_TYPES.add(Short.class);
        WRAPPER_TYPES.add(Integer.class);
        WRAPPER_TYPES.add(Long.class);
        WRAPPER_TYPES.add(Float.class);
        WRAPPER_TYPES.add(Double.class);
    }


    private boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    public String toJson(Object object) throws IllegalAccessException {
        Class clazz = object.getClass();

        if (clazz.equals(String.class)) {
            return String.format("\"%s\"", object);
        }

        if (object.getClass().isPrimitive()
                || isWrapperType(object.getClass())
                || object.getClass().isEnum()) {
            return object.toString();
        }

        if (object.getClass().isArray()) {
            List<String> strings = new ArrayList<>();
            int length = Array.getLength(object);

            for (int i = 0; i < length; i++) {
                Object item = Array.get(object, i);
                strings.add(toJson(item));
            }

            return String.format("[%s]", String.join(",", strings));
        }

        if (object instanceof Collection) {
            List<String> strings = new ArrayList<>();

            for (Object item : ((Collection) object)) {
                strings.add(toJson(item));
            }

            return String.format("[%s]", String.join(",", strings));
        }

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
