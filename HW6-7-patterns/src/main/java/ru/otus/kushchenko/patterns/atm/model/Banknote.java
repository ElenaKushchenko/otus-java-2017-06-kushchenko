package ru.otus.kushchenko.patterns.atm.model;

import lombok.*;

/**
 * Купюра.
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Banknote {

    /**
     * Номинал купюры.
     */
    @NonNull
    private final Nominal nominal;

    /**
     * Серийный номер купюры.
     */
    private String serialNumber;

}
