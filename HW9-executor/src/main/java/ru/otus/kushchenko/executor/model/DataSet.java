package ru.otus.kushchenko.executor.model;

import lombok.Getter;

import javax.persistence.*;

@Getter

@MappedSuperclass
public abstract class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
}
