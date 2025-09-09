package com.chollsak.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {

    private String firstName;

    private String lastName;

    private String ShopName;

    private LocalDate dateOfBirth;


    private int point;
    private String profileImgUrl;

    @NotBlank(message = "user_id is required.")
    private Long user_id;

}
