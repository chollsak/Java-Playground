package com.chollsak.demo.dtos;

import com.chollsak.demo.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid")
    private String email;

    private Role role = Role.CUSTOMER;

    public void setRole(Role role){
        this.role = (role != null) ? role : Role.CUSTOMER;
    }

}