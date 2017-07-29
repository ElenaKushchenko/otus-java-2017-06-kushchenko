package ru.otus.atm.strategy.impl;


import ru.otus.atm.machine.Cell;

import java.util.Comparator;
import java.util.List;

/**
 * Выдать с разменом.
 */
public class SmallFirstWithdrawStrategy extends AbstractWithdrawStrategy {

    @Override
    protected void sort(List<Cell> cells) {
        cells.sort(Comparator.comparingInt((Cell cell) -> cell.getNominal().getValue()));
    }

}
