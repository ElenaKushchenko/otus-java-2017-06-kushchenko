package ru.otus.kushchenko.hibernate.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString

@MappedSuperclass
public abstract class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "db_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    protected long id;
}
