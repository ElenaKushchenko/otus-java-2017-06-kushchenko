package ru.otus.kushchenko.patterns.atm.department;

import ru.otus.kushchenko.patterns.atm.service.Service;

/**
 * ATM департамент.
 */
public interface Department {

    /**
     * @return текущая сумма денежных средств всех банкоматов департамента
     */
    int getBalance();

    /**
     * Выполнить сервисное обслуживание всех банкоматов департамента.
     *
     * @param service сервис
     */
    void doService(Service service);
}
