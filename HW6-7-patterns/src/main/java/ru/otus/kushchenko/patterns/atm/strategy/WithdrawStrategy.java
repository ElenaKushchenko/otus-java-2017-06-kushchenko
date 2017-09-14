package ru.otus.kushchenko.patterns.atm.strategy;

import ru.otus.kushchenko.patterns.atm.machine.Cell;
import ru.otus.kushchenko.patterns.atm.model.Banknote;

import java.util.List;

/**
 * Стратегия выдачи денежных средств.
 */
public interface WithdrawStrategy {

    /**
     * Выдать денежные средства.
     *
     * @param sum запрошенная сумма
     * @param cells ячейки банкомата, с которого производится выдача
     * @return купюры
     */
    List<Banknote> withdraw(int sum, List<Cell> cells);

}
