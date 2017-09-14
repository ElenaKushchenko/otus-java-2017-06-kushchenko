package ru.otus.kushchenko.cache.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet {

    @Column(name = "street")
    private String street;


    public AddressDataSet(Long id, AddressDataSet sourceAddress) {
        this.id = id;
        this.street = sourceAddress.street;
    }
}
