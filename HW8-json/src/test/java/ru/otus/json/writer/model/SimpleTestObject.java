package ru.otus.json.writer.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTestObject extends AbstractTestObject {
    protected int simpleInt;
    protected String simpleString;
    protected TestEnum simpleEnum;
    protected double[] simpleArray;
}
