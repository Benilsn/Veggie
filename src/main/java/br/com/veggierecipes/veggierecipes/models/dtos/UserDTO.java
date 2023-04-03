package br.com.veggierecipes.veggierecipes.models.dtos;

import br.com.veggierecipes.veggierecipes.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private String image_address;
}