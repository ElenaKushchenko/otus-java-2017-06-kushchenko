package ru.otus.kushchenko.ioc.model;

import lombok.*;
import ru.otus.kushchenko.ioc.model.enums.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "password")
    private UUID password;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PhoneDataSet> phones = new ArrayList<>();


    public static UserDataSet of(Long id, UserDataSet source) {
        UserDataSet user = new UserDataSet(
                source.name,
                source.role,
                source.password,
                source.age,
                source.address,
                source.phones
        );
        user.id = id;

        return user;
    }
}
