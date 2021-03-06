package ru.otus.kushchenko.patterns.atm.strategy.impl;


import ru.otus.kushchenko.patterns.atm.machine.Cell;

import java.util.Comparator;

/**
 * Выдать с разменом.
 */
public class SmallFirstWithdrawStrategy extends AbstractWithdrawStrategy {

    @Override
    protected Comparator<Cell> getComparator() {
        return Comparator.comparingInt((Cell cell) -> cell.getNominal().getValue());
    }

}
