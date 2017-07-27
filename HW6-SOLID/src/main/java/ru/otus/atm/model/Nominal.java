package ru.otus.atm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Nominal {
    ONE(1),
    FIVE(5),
    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSAND(5000);

    /**
     * Номинал в целочисленном представлении
     */
    @NonNull
    private final Integer intValue;


    public static Nominal resolveByIntValue(Integer value) {
        return Stream.of(values())
                .filter(e -> e.intValue.equals(value))
                .findFirst()
                .orElse(null);
    }

}
