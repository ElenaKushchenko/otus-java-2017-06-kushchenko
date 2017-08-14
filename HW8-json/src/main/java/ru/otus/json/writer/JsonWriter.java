package ru.otus.json.writer;

public interface JsonWriter {

    String toJson(Object object) throws IllegalAccessException;

}
