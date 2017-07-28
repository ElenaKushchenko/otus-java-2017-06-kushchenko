package ru.otus.atm.machine.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import ru.otus.atm.machine.Cell;
import ru.otus.atm.model.Banknote;
import ru.otus.atm.model.Nominal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@EqualsAndHashCode
public class CellImpl implements Cell {

    @Getter
    @NonNull
    private final Nominal nominal;

    private final List<Banknote> banknotes;


    CellImpl(final Nominal nominal) {
        this.nominal = nominal;
        this.banknotes = new ArrayList<>();
    }


    @Override
    public int getSize() {
        return this.banknotes.size();
    }

    @Override
    public int getBalance() {
        return this.nominal.getValue() * this.getSize();
    }

    @Override
    public Optional<Banknote> put(final Banknote banknote) {
        if (this.nominal.equals(banknote.getNominal())) {
            this.banknotes.add(banknote);
            return Optional.empty();
        } else {
            return Optional.of(banknote);
        }
    }

    @Override
    public List<Banknote> put(final List<Banknote> banknotes) {
        final List<Banknote> result = new ArrayList<>();

        banknotes.forEach(
                banknote -> this.put(banknote).ifPresent(result::add)
        );

        return result;
    }

    @Override
    public List<Banknote> take(final int count) throws IllegalArgumentException {
        if (count > banknotes.size()) {
            throw new IllegalArgumentException();
        }

        final int beforeSize = banknotes.size();
        final List<Banknote> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(banknotes.remove(beforeSize - i - 1));
        }

        return result;
    }
}
