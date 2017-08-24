package ru.otus.model;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter

@MappedSuperclass
public abstract class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private BigInteger id;
}
