package ru.otus.json.writer.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JsonFormatterTest {

    @Test
    void formatString() {
        final String testString = "Hello World!";

        final String expected = "\"Hello World!\"";
        final String actual = JsonFormatter.formatString(testString);

        assertEquals(expected, actual);
    }

    @Test
    void formatArray() {
        final List<String> testArray = Arrays.asList(
                "1",
                "2.34",
                "true",
                "Hello",
                "null"
        );

        final String expected = "[1,2.34,true,Hello,null]";
        final String actual = JsonFormatter.formatArray(testArray);

        assertEquals(expected, actual);
    }

    @Test
    void formatObject() {
        final Map<String, String> testMap = new HashMap<>();

        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        testMap.put("key3", "value3");

        final String expected = "{\"key1\":value1,\"key2\":value2,\"key3\":value3}";
        final String actual = JsonFormatter.formatObject(testMap);

        assertEquals(expected, actual);
    }

}