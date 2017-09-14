package ru.otus.kushchenko.cache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
