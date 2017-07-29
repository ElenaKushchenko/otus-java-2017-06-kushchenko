package ru.otus.atm.machine;

import ru.otus.atm.model.Banknote;
import ru.otus.atm.model.Nominal;
import ru.otus.atm.service.Service;

import java.util.List;
import java.util.Optional;

/**
 * Ячейка для купюр определенного номинала.
 */
public interface Cell {

    /**
     * @return номинал, для которого предназначена ячейка
     */
    Nominal getNominal();

    /**
     * @return первоначальное количество купюр в ячейке
     */
    int getInitialSize();

    /**
     * @return текущее количество купюр в ячейке
     */
    int getSize();

    /**
     * @return текущая сумма денежных средств в ячейке
     */
    int getBalance();

    /**
     * Внести купюру.
     *
     * @param banknote купюра
     * @return купюра, если ее номинал не соответствует номиналу ячейки
     */
    Optional<Banknote> deposit(Banknote banknote);

    /**
     * Внести купюры.
     *
     * @param banknotes купюры
     * @return купюры, номинал которых не соответствует номиналу ячейки
     */
    List<Banknote> deposit(List<Banknote> banknotes);

    /**
     * Получить купюры.
     *
     * @param count количество
     * @return купюры
     */
    List<Banknote> withdraw(int count);

    /**
     * Произвести сервисное обслуживание.
     *
     * @param service сервис
     */
    void doService(Service service);
}
