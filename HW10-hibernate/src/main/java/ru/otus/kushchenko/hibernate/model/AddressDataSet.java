package ru.otus.kushchenko.hibernate.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet {

    @Column(name = "street")
    private String street;
}
