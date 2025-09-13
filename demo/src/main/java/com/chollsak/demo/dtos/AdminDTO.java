package com.chollsak.demo.dtos;

import com.chollsak.demo.entities.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private UUID userId;

    public AdminDTO(Admin admin){
        this.id = admin.getId();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.userId = admin.getUserId();
    }
}
