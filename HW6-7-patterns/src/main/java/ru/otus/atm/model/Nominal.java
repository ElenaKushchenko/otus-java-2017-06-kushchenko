package ru.otus.atm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Номинал банкноты.
 */
@Getter
@AllArgsConstructor
@ToString
public enum Nominal {

    TEN(10),
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSAND(5000);

    /**
     * Целочисленное значение номинала.
     */
    private final int value;

    /**
     * Получить номинал по численному значению.
     *
     * @param nominal целочисленное значение номинала
     * @return номинал или null, если соответствующего
     */
    public static Optional<Nominal> valueOf(int nominal) {
        return Optional.ofNullable(
                Stream.of(values())
                        .filter(e -> Objects.equals(e.value, nominal))
                        .findFirst()
                        .orElse(null)
        );
    }

}
