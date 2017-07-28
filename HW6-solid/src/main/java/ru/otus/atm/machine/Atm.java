package ru.otus.atm.machine;

import ru.otus.atm.model.Banknote;

import java.util.List;

/**
 * Интерфейс банкомата.
 */
public interface Atm {

    /**
     * Получить текущую сумму денежных средств в банкомате.
     *
     * @return текущая сумма денежных средств в банкомате
     */
    int getBalance();

    /**
     * Внести купюры.
     *
     * @param banknotes купюры
     * @return купюры, не прошедшие проверку
     */
    List<Banknote> putCash(List<Banknote> banknotes);

    /**
     * Снять денежные средства.
     *
     * @param sum сумма
     * @return денежные средства в указанном объеме
     */
    List<Banknote> takeCash(int sum);

}
