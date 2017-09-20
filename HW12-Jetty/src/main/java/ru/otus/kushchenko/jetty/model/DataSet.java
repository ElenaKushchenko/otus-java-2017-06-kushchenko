package ru.otus.kushchenko.jetty.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString

@MappedSuperclass
public abstract class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "db_sequence", allocationSize = 1)
    @Column(name = "id")
    protected Long id;
}
