package ru.otus.atm.strategy.impl;

import ru.otus.atm.machine.Cell;
import ru.otus.atm.model.Banknote;
import ru.otus.atm.strategy.WithdrawStrategy;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractWithdrawStrategy implements WithdrawStrategy {

    public List<Banknote> withdraw(int sum, List<Cell> cells) { //ToDo
        sort(cells);

        List<Banknote> result = new ArrayList<>();

        return result;
    }

    protected abstract void sort(List<Cell> cells);

}
