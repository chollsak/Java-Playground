package com.chollsak.demo.dtos;

import com.chollsak.demo.enums.Role;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String email;
    private Role role;

}
