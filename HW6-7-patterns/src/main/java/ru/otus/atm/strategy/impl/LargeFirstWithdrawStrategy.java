package ru.otus.atm.strategy.impl;


import ru.otus.atm.machine.Cell;

import java.util.Comparator;
import java.util.List;


public class LargeFirstWithdrawStrategy extends AbstractWithdrawStrategy {

    @Override
    protected void sort(List<Cell> cells) {
        cells.sort(Comparator.comparingInt((Cell cell) -> cell.getNominal().getValue()).reversed());
    }

}
