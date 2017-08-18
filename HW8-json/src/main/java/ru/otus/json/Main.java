package ru.otus.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.json.example.ExtendedTestObject;
import ru.otus.json.example.SimpleTestObject;
import ru.otus.json.writer.SimpleJsonWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String... args) throws IllegalAccessException, ClassNotFoundException {
        SimpleTestObject testObject = new ExtendedTestObject(1, "test", new double[]{1.23, 4.56, 4.0}, 98,
                Arrays.asList("str1", "str2", "str3"));
        testObject.testMethod();

        Map<Integer, SimpleTestObject> map = new HashMap<>();
        map.put(123, testObject);

        Gson gson = new GsonBuilder()
                .create();
        System.out.println(gson.toJson(map));

        SimpleJsonWriter writer = new SimpleJsonWriter();
        System.out.println(writer.toJson(map));
    }
}
