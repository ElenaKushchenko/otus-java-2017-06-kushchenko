package ru.otus.json.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.otus.json.writer.model.ExtendedTestObject;
import ru.otus.json.writer.model.TestEnum;
import ru.otus.json.writer.model.TestObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SimpleJsonWriterTest {
    private final Gson gson = new GsonBuilder().create();
    private final JsonWriter myJson = new SimpleJsonWriter();

    @Test
    void stringToJson() throws IllegalAccessException {
        final String testString = "Hello World!";

        assertEquals(this.gson.toJson(testString), this.myJson.toJson(testString));
    }

    @Test
    void enumToJson() throws IllegalAccessException {
        final TestEnum testEnum = TestEnum.SECOND;

        assertEquals(this.gson.toJson(testEnum), this.myJson.toJson(testEnum));
    }

    @Test
    void primitiveToJson() throws IllegalAccessException {
        final int testInt = 12345;
        final double testDouble = 12.5D;
        final boolean testBoolean = true;
        final char testChar = 'h';

        assertEquals(this.gson.toJson(testInt), this.myJson.toJson(testInt));
        assertEquals(this.gson.toJson(testDouble), this.myJson.toJson(testDouble));
        assertEquals(this.gson.toJson(testBoolean), this.myJson.toJson(testBoolean));
        assertEquals(this.gson.toJson(testChar), this.myJson.toJson(testChar));
    }

    @Test
    void wrapperToJson() throws IllegalAccessException {
        final Integer testInteger = 12345;
        final Double testDouble = 12.5D;
        final Boolean testBoolean = true;
        final Character testCharacter = 'h';

        assertEquals(this.gson.toJson(testInteger), this.myJson.toJson(testInteger));
        assertEquals(this.gson.toJson(testDouble), this.myJson.toJson(testDouble));
        assertEquals(this.gson.toJson(testBoolean), this.myJson.toJson(testBoolean));
        assertEquals(this.gson.toJson(testCharacter), this.myJson.toJson(testCharacter));
    }

    @Test
    void arrayToJson() throws IllegalAccessException {
        final int[] testArray = new int[]{1, 2, 3, 4, 5};

        assertEquals(this.gson.toJson(testArray), this.myJson.toJson(testArray));
    }

    @Test
    void collectionToJson() throws IllegalAccessException {
        final List<Object> testList = Arrays.asList(
                "first",
                1,
                TestEnum.THIRD,
                2.45,
                false
        );

        assertEquals(this.gson.toJson(testList), this.myJson.toJson(testList));
    }

    @Test
    void mapToJson() throws IllegalAccessException {
        final Map<Integer, Object> testMap = new HashMap<>();

        testMap.put(1, "first");
        testMap.put(2, 1);
        testMap.put(3, TestEnum.THIRD);
        testMap.put(4, 2.45);
        testMap.put(5, false);

        assertEquals(this.gson.toJson(testMap), this.myJson.toJson(testMap));
    }

    @Test
    void objectToJson() throws IllegalAccessException {
        final double[] testDoubleArray = new double[]{2.5, 3, 6.789};
        final List<String> testStringList = Arrays.asList("test1", "test2", "test3");
        final Map<Integer, String> testMap = new HashMap<>();

        testMap.put(1, "value1");
        testMap.put(2, "value2");
        testMap.put(3, "value3");

        final TestObject testObject = new ExtendedTestObject("Hello", 1, "World", TestEnum.FIRST, testDoubleArray, 24, testStringList, testMap);

        assertEquals(this.gson.toJson(testObject), this.myJson.toJson(testObject));
    }
}