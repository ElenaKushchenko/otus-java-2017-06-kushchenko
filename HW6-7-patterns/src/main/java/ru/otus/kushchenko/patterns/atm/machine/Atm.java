package ru.otus.kushchenko.patterns.atm.machine;

import ru.otus.kushchenko.patterns.atm.model.Banknote;
import ru.otus.kushchenko.patterns.atm.service.Service;
import ru.otus.kushchenko.patterns.atm.strategy.WithdrawStrategy;

import java.util.List;

/**
 * Банкомат.
 */
public interface Atm {

    /**
     * @return текущая сумма денежных средств в банкомате
     */
    int getBalance();

    /**
     * Внести купюры.
     *
     * @param banknotes купюры
     * @return купюры, не прошедшие проверку
     */
    List<Banknote> depositCash(List<Banknote> banknotes);

    /**
     * Снять денежные средства.
     *
     * @param sum запрошенная сумма
     * @param strategy стратегия выдачи
     * @return денежные средства в указанном объеме
     */
    List<Banknote> withdrawCash(int sum, WithdrawStrategy strategy);

    /**
     * Произвести сервисное обслуживание.
     *
     * @param service сервис
     */
    void doService(Service service);
}
