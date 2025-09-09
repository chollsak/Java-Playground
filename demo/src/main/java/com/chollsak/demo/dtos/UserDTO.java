package com.chollsak.demo.dtos;

import com.chollsak.demo.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private Role role;

}
