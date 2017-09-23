package ru.otus.kushchenko.hibernate.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet {

    @NonNull
    @Column(name = "street")
    private String street;

    @OneToOne(mappedBy = "address")
    private UserDataSet user;


    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + street + '\'' +
                ", id=" + id +
                '}';
    }
}
