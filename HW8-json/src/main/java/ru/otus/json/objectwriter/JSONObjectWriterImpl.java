package ru.otus.json.objectwriter;

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Elena on 04.08.2017.
 */
public class JSONObjectWriterImpl implements JSONObjectWriter {
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> result = new HashSet<>();

        result.add(Boolean.class);
        result.add(Character.class);
        result.add(Byte.class);
        result.add(Short.class);
        result.add(Integer.class);
        result.add(Long.class);
        result.add(Float.class);
        result.add(Double.class);

        return result;
    }

    private boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    @Override
    public String toJson(Object object) throws IllegalAccessException {
        List<String> strings = new ArrayList<>();
        Class clazz = object.getClass();

        while (!clazz.equals(Object.class)) {
            for (Field field : clazz.getDeclaredFields()) {
                if (Modifier.isTransient(field.getModifiers())) {
                    break;
                }
                field.setAccessible(true);

                Class<?> type = field.getType();

                if (type.isPrimitive() || isWrapperType(type) || type.equals(String.class)) {

                }

                Object value = field.get(object);

                strings.add("\"" + field.getName() + "\":" + value);
            }

            clazz = clazz.getSuperclass();
        }

        return String.format("{%s}", StringUtils.join(strings, ","));
    }
}
