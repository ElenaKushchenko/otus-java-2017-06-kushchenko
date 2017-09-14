package ru.otus.kushchenko.patterns.atm.machine.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import ru.otus.kushchenko.patterns.atm.machine.Cell;
import ru.otus.kushchenko.patterns.atm.model.Banknote;
import ru.otus.kushchenko.patterns.atm.model.Nominal;
import ru.otus.kushchenko.patterns.atm.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@EqualsAndHashCode
public class CellImpl implements Cell {

    @Getter
    @NonNull
    private final Nominal nominal;

    @NonNull
    private final List<Banknote> banknotes;

    @Getter
    private final int initialSize;


    public CellImpl(final Nominal nominal) {
        this.nominal = nominal;
        this.banknotes = new ArrayList<>();
        this.initialSize = 0;
    }

    public CellImpl(Nominal nominal, List<Banknote> banknotes) {
        this.nominal = nominal;
        this.banknotes = banknotes;
        this.initialSize = banknotes.size();
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
    public Optional<Banknote> deposit(final Banknote banknote) {
        if (this.nominal.equals(banknote.getNominal())) {
            this.banknotes.add(banknote);
            return Optional.empty();
        } else {
            return Optional.of(banknote);
        }
    }

    @Override
    public List<Banknote> deposit(final List<Banknote> banknotes) {
        final List<Banknote> result = new ArrayList<>();

        banknotes.forEach(
                banknote -> this.deposit(banknote).ifPresent(result::add)
        );

        return result;
    }

    @Override
    public List<Banknote> withdraw(final int count) throws IllegalArgumentException {
        if (count > this.banknotes.size()) {
            throw new IllegalArgumentException("Недостаточно денежных средств для выдачи.");
        }

        return IntStream.range(0, count)
                .mapToObj(i -> this.banknotes.remove(this.banknotes.size() - 1))
                .collect(Collectors.toList());
    }

    @Override
    public void doService(Service service) {
        service.visit(this);
    }
}
