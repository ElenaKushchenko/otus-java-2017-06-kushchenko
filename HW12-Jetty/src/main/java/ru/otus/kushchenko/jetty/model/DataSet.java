package ru.otus.kushchenko.jetty.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString

@MappedSuperclass
public abstract class DataSet {

    @Id
    @Column(name = "id")
    protected Long id;
}
