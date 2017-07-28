package ru.otus.atm.machine;

import ru.otus.atm.model.Banknote;
import ru.otus.atm.model.Nominal;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс ячейки для купюр одного номинала.
 */
public interface Cell {

    /**
     * Получить номинал, для которого предназначена ячейка.
     *
     * @return номинал, для которого предназначена ячейка
     */
    Nominal getNominal();

    /**
     * Получить количество купюр в ячейке.
     *
     * @return количество купюр в ячейке
     */
    int getSize();

    /**
     * Получить текущую сумму денежных средств в ячейке.
     *
     * @return текущая сумма денежных средств в ячейке
     */
    int getBalance();

    /**
     * Внести купюру.
     *
     * @param banknote купюра
     * @return купюра, если ее номинал не соответствует номиналу ячейки
     */
    Optional<Banknote> put(Banknote banknote);

    /**
     * Внести купюры.
     *
     * @param banknotes купюры
     * @return купюры, номинал которых не соответствует номиналу ячейки
     */
    List<Banknote> put(List<Banknote> banknotes);

    /**
     * Получить купюры.
     *
     * @param count количество
     * @return купюры
     */
    List<Banknote> take(int count);
}
