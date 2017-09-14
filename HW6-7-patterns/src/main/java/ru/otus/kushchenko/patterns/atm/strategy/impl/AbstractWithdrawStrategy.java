package ru.otus.kushchenko.patterns.atm.strategy.impl;

import ru.otus.kushchenko.patterns.atm.machine.Cell;
import ru.otus.kushchenko.patterns.atm.model.Banknote;
import ru.otus.kushchenko.patterns.atm.strategy.WithdrawStrategy;

import java.util.*;
import java.util.stream.Collectors;


public abstract class AbstractWithdrawStrategy implements WithdrawStrategy {

    public List<Banknote> withdraw(int sum, List<Cell> cells) {
        List<Cell> sortedCells = cells.stream()
                .sorted(getComparator())
                .collect(Collectors.toList());

        int remainder = sum;                                                    //сумма, которую осталось выдать
        final Map<Cell, Integer> resultActions = new HashMap<>();               //<Ячейка, Число купюр>

        for (int i = 0; (i < sortedCells.size() && remainder > 0); i++) {             //проход по ячейкам пока не выдана полная сумма
            final Cell cell = sortedCells.get(i);

            final int banknoteCount = remainder / cell.getNominal().getValue();
            if (cell.getSize() - banknoteCount >= 0) {                          //в ячейке достаточно купюр
                resultActions.put(cell, banknoteCount);
                remainder -= cell.getNominal().getValue() * banknoteCount;
            } else {
                resultActions.put(cell, cell.getSize());
                remainder -= cell.getBalance();
            }
        }

        if (remainder > 0) {
            throw new IllegalArgumentException("Невозможно выдать указанную сумму.");
        }

        final List<Banknote> result = new ArrayList<>();
        resultActions.forEach((key, value) -> result.addAll(key.withdraw(value)));

        return result;
    }

    protected abstract Comparator<Cell> getComparator();

}
