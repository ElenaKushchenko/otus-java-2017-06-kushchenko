package ru.otus.json.writer.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JsonFormatter {

    public static String formatString(Object obj) {
        return String.format("\"%s\"", obj);
    }

    public static String formatArray(List<String> obj) {
        return String.format("[%s]", String.join(",", obj));
    }

    public static String formatObject(Map<String, String> obj) {
        return String.format("{%s}",
                obj.entrySet()
                        .stream()
                        .map(item -> String.format("%s:%s", formatString(item.getKey()), item.getValue()))
                        .collect(Collectors.joining(","))
        );
    }
}
