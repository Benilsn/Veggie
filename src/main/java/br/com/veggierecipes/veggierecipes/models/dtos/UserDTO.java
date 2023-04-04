package br.com.veggierecipes.veggierecipes.models.dtos;

import org.springframework.security.core.GrantedAuthority;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty(message = "Name cannot be empty!")
    private String name;

    @Email(message = "Please choose a valid e-mail!")
    private String email;

    @NotEmpty(message = "Password cannot be empty!")
    @Size(message = "Password must have at least 3 chars.")
    private String password;

    private GrantedAuthority role;
    private String image_address;
}