package ru.otus.atm.strategy.impl;

import ru.otus.atm.machine.Cell;
import ru.otus.atm.model.Banknote;
import ru.otus.atm.strategy.WithdrawStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractWithdrawStrategy implements WithdrawStrategy {

    public List<Banknote> withdraw(int sum, List<Cell> cells) {
        sort(cells);

        int remainder = sum;                                                    //сумма, которую осталось выдать
        final Map<Cell, Integer> resultActions = new HashMap<>();               //<Ячейка, Число купюр>

        for (int i = 0; (i < cells.size() && remainder > 0); i++) {             //проход по ячейкам пока не выдана полная сумма
            final Cell cell = cells.get(i);

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

    protected abstract void sort(List<Cell> cells);

}
