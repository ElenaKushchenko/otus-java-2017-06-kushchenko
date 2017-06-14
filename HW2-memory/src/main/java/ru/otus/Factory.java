package ru.otus;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by Elena on 14.06.2017.
 */

public class Factory {
    private final static Map<String, Supplier<Object>> map = new HashMap<>();

    static {
        map.put("OBJECT", Object::new);
        map.put("STRING", String::new);
    }

    public Object create(String type){
        Supplier<Object> supplier = map.get(type.toUpperCase());
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException(type + " type is not supported yet...");
    }
}
