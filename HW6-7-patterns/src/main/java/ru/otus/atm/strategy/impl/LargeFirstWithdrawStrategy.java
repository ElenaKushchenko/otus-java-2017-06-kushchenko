package ru.otus.atm.strategy.impl;


import ru.otus.atm.machine.Cell;

import java.util.Comparator;

/**
 * Выдать крупными.
 */
public class LargeFirstWithdrawStrategy extends AbstractWithdrawStrategy {

    @Override
    protected Comparator<Cell> getComparator() {
        return Comparator.comparingInt((Cell cell) -> cell.getNominal().getValue()).reversed();
    }

}
