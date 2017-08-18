package ru.otus.json.writer;

import java.util.HashSet;
import java.util.Set;


public class TypeResolver {
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


    public static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }
}
