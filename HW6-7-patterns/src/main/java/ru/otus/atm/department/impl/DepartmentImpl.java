package ru.otus.atm.department.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import ru.otus.atm.department.Department;
import ru.otus.atm.machine.Atm;
import ru.otus.atm.service.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public class DepartmentImpl implements Department {

    @NonNull
    private final List<Atm> atmList;


    public DepartmentImpl() {
        this.atmList = new ArrayList<>();
    }


    public boolean add(Atm atm) {
        return this.atmList.add(atm);
    }

    public boolean addAll(List<Atm> atmList) {
        return this.atmList.addAll(atmList);
    }

    @Override
    public int getBalance() {
        return this.atmList.stream()
                .mapToInt(Atm::getBalance)
                .sum();
    }

    @Override
    public void doService(Service service) {
        this.atmList.forEach(atm -> atm.doService(service));
    }

}
