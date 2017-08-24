package ru.otus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter

@Entity
@Table(name = "USER")
public class UserDataSet extends DataSet {

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private int age;
}
