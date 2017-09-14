package ru.otus.kushchenko.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PhoneDataSet> phones = new ArrayList<>();
}
