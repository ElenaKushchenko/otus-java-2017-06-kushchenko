package ru.otus.kushchenko.jetty.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

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


    public UserDataSet(Long id, String name, int age, AddressDataSet address, List<PhoneDataSet> phones) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phones = phones;
    }


    public static UserDataSet of(Long id, UserDataSet source) {
        return new UserDataSet(id, source.name, source.age, source.address, source.phones);
    }
}
