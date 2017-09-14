package ru.otus.kushchenko.cache.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public UserDataSet(Long id, UserDataSet sourceUser) {
        this.id = id;
        this.name = sourceUser.name;
        this.age = sourceUser.age;
        this.address = new AddressDataSet(null, sourceUser.address);
        this.phones = sourceUser.phones
                .stream()
                .map(phone -> new PhoneDataSet(null, phone))
                .collect(Collectors.toList());
    }
}
