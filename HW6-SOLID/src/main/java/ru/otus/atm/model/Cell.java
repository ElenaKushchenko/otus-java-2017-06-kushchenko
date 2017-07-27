package ru.otus.atm.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class Cell {

    @Getter
    @NonNull
    private final Nominal nominal;

    private final List<Banknote> banknotes;


    Cell(final Nominal nominal) {
        this.nominal = nominal;
        this.banknotes = new ArrayList<>();
    }


    /**
     * Получить количество купюр в ячейке.
     *
     * @return количество купюр в ячейке
     */
    public int getSize() {
        return this.banknotes.size();
    }

    /**
     * Получить текущую сумму денежных средств в ячейке.
     *
     * @return текущая сумма денежных средств в ячейке
     */
    public int getBalance() {
        return this.nominal.getIntValue() * this.getSize();
    }


    /**
     * Добавить денежные средства.
     *
     * @param banknote купюра
     * @return банкнота не прошедшая проверку
     */
    public Banknote putCash(final Banknote banknote) {
        final Banknote result = null;

        if (this.nominal.equals(banknote.getNominal())) {
            this.banknotes.add(banknote);
        }

        return result;
    }

    public List<Banknote> putCash(final List<Banknote> banknotes) {
        final List<Banknote> result = new ArrayList<>();

        banknotes.forEach(
                banknote -> {
                    result.add(this.putCash(banknote));
                }
        );

        return result;
    }

    public List<Banknote> takeBanknote(final int count) {
        if (count > banknotes.size()) {
            //TODO
            throw new RuntimeException();
        }

        final int beforeSize = banknotes.size();
        final List<Banknote> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(banknotes.remove(beforeSize - i - 1));
        }

        return result;
    }
}
