package ru.otus.atm.strategy;

import ru.otus.atm.machine.Cell;
import ru.otus.atm.model.Banknote;

import java.util.List;

/**
 * Стратегия выдачи денежных средств.
 */
public interface WithdrawStrategy {

    /**
     * Снять денежные средства.
     *
     * @param sum запрошенная сумма
     * @param cells ячейки банкомата, с которого производится выдача
     * @return купюры
     */
    List<Banknote> withdraw(int sum, List<Cell> cells);

}
