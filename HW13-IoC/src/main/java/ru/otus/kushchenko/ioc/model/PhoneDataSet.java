package ru.otus.kushchenko.ioc.model;

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
@Table(name = "phones")
public class PhoneDataSet extends DataSet {

    @Column(name = "number")
    private String number;
}
