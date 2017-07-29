package ru.otus.atm.service;

import ru.otus.atm.machine.Cell;

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
