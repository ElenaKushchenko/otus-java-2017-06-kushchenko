package ru.otus.json.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedTestObject extends SimpleTestObject {
    private Integer newField;

    public ExtendedTestObject(int num, String str, double[] dArray, Integer newField) {
        super(num, str, dArray);
        this.newField = newField;
    }
}
