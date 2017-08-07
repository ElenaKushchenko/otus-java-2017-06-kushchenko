package ru.otus.json.example;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTestObject extends AbstractTestObject {
    private int num;
    private String str;
    private double[] dArray;
}
