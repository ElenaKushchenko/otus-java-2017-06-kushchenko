package ru.otus.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.json.example.ExtendedTestObject;
import ru.otus.json.example.SimpleTestObject;
import ru.otus.json.objectwriter.JSONObjectWriter;
import ru.otus.json.objectwriter.JSONObjectWriterImpl;

/**
 * Created by Elena on 03.08.2017.
 */
public class Main {

    public static void main(String... args) throws IllegalAccessException {
        SimpleTestObject testObject = new ExtendedTestObject(1, "test", new double[]{1.23, 4.56, 4.0}, 98);
        testObject.testMethod();

        Gson gson = new GsonBuilder()
                .create();
        System.out.println(gson.toJson(testObject));

        JSONObjectWriter writer = new JSONObjectWriterImpl();
        System.out.println(writer.toJson(testObject));
    }
}
