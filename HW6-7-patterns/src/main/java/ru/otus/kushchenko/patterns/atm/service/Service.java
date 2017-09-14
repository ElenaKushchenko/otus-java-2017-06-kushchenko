package ru.otus.kushchenko.patterns.atm.service;

import ru.otus.kushchenko.patterns.atm.machine.Cell;

/**
 * Сервис обслуживания банкоматов.
 */
public interface Service {

    /**
     * Произвести сервисное обслуживание ячейки банкомата.
     *
     * @param cell ячейка банкомата
     */
    void visit(Cell cell);

}
