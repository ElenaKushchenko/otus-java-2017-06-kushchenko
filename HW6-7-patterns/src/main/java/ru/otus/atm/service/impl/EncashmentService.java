package ru.otus.atm.service.impl;

import ru.otus.atm.machine.Cell;
import ru.otus.atm.model.Banknote;
import ru.otus.atm.service.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сервис инкассации.
 */
public class EncashmentService implements Service {

    @Override
    public void visit(Cell cell) {
        final int currentSize = cell.getSize();
        final int initialSize = cell.getInitialSize();

        if (currentSize > initialSize) {
            cell.withdraw(currentSize - initialSize);
        }

        if (currentSize < initialSize) {
            List<Banknote> banknotes = Stream.generate(() -> new Banknote(cell.getNominal()))
                    .limit(initialSize - currentSize)
                    .collect(Collectors.toList());

            cell.deposit(banknotes);
        }
    }

}
