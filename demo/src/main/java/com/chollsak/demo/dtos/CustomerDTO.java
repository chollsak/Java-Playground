package com.chollsak.demo.dtos;


import com.chollsak.demo.entities.Customer;
import com.chollsak.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String ShopName;
    private LocalDate dateOfBirth;
    private int point;
    private String profileImgUrl;
    private Long userId;

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.ShopName = customer.getShopName();
        this.dateOfBirth = customer.getDateOfBirth();
        this.point = customer.getPoints();
        this.profileImgUrl = customer.getImgPath();
        this.userId = customer.getUserId();
    }

}
