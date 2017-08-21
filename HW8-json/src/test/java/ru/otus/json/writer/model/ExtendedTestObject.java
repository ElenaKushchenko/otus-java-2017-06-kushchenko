package ru.otus.json.writer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedTestObject extends SimpleTestObject {
    protected Integer extendedInteger;
    protected List<String> extendedList;
    protected Map<Integer, String> extendedMap;
}
