package ru.otus.json.objectwriter;

/**
 * Created by Elena on 04.08.2017.
 */
public interface JSONObjectWriter {

    public String toJson(Object object) throws IllegalAccessException;
}
