package ru.otus.kushchenko.ms.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.kushchenko.ms.model.AddressDataSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String street;


    public AddressDTO(AddressDataSet address) {
        this.id = address.getId();
        this.street = address.getStreet();
    }
}
