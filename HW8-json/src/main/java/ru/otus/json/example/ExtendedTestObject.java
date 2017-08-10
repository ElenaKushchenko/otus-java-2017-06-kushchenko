package ru.otus.json.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedTestObject extends SimpleTestObject {
    private Integer newField;
    private List<String> stringList;

    public ExtendedTestObject(int num, String str, double[] dArray, Integer newField, List<String> stringList) {
        super(num, str, dArray);
        this.newField = newField;
        this.stringList = stringList;
    }
}
