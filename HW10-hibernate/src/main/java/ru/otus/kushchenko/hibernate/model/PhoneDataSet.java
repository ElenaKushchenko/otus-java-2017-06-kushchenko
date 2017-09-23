package ru.otus.kushchenko.hibernate.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "phones")
public class PhoneDataSet extends DataSet {

    @NonNull
    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDataSet user;


    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                ", id=" + id +
                '}';
    }
}
