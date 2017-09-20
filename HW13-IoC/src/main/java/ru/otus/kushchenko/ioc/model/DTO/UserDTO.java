package ru.otus.kushchenko.ioc.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.kushchenko.ioc.model.UserDataSet;
import ru.otus.kushchenko.ioc.model.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private Role role;
    private int age;
    private AddressDTO address;
    private List<PhoneDTO> phones = new ArrayList<>();


    public UserDTO(UserDataSet user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.age = user.getAge();
        this.address = new AddressDTO(user.getAddress());
        this.phones = user.getPhones()
                .stream()
                .map(PhoneDTO::new)
                .collect(Collectors.toList());
    }
}
