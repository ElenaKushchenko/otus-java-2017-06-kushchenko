package ru.otus.kushchenko.jetty.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.kushchenko.jetty.model.PhoneDataSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    private Long id;
    private String number;

    public PhoneDTO(PhoneDataSet phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
    }
}
