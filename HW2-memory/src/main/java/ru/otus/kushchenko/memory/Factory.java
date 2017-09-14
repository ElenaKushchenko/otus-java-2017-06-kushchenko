package ru.otus.kushchenko.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by Elena on 14.06.2017.
 */

public class Factory {
    private final static Map<Type, Supplier<Object>> map = new HashMap<>();

    static {
        map.put(Type.OBJECT, Object::new);
        map.put(Type.STRING, () -> new String(new char[0]));
        map.put(Type.BOOLEAN, () -> false);
        map.put(Type.INT, () -> Integer.MIN_VALUE);
        map.put(Type.DOUBLE, () -> Double.MIN_VALUE);
        map.put(Type.EMPTY_ARRAY, () -> new int[0]);
    }

    public Object create(Type type){
        Supplier<Object> supplier =  map.get(type);
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException(type + " type is not supported yet...");
    }
}
