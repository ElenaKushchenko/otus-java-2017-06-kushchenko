package ru.otus.kushchenko.patterns.atm.machine.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.otus.kushchenko.patterns.atm.machine.Atm;
import ru.otus.kushchenko.patterns.atm.machine.Cell;
import ru.otus.kushchenko.patterns.atm.model.Banknote;
import ru.otus.kushchenko.patterns.atm.service.Service;
import ru.otus.kushchenko.patterns.atm.strategy.WithdrawStrategy;

import java.util.List;


@AllArgsConstructor
public class AtmImpl implements Atm {

    @NonNull
    private final List<Cell> cells;


    @Override
    public int getBalance() {
        return this.cells.stream()
                .mapToInt(Cell::getBalance)
                .sum();
    }

    @Override
    public List<Banknote> depositCash(List<Banknote> banknotes) {
        List<Banknote> result = banknotes;

        for (Cell cell : this.cells) {
            result = cell.deposit(result);
        }

        return result;
    }

    @Override
    public List<Banknote> withdrawCash(int sum, WithdrawStrategy strategy) {
        if (sum > getBalance()) {
            throw new IllegalArgumentException("Недостаточно денежных средств для выдачи.");
        }

        return strategy.withdraw(sum, this.cells);
    }

    @Override
    public void doService(Service service) {
        this.cells.forEach(cell -> cell.doService(service));
    }

}
