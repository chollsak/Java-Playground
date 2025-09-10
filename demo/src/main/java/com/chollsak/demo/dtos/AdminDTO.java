package com.chollsak.demo.dtos;

import com.chollsak.demo.entities.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long userId;

    public AdminDTO(Admin admin){
        this.id = admin.getId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.userId = admin.getUserId();
    }
}
