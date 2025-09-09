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
public class CustomerUpdateRequestDTO {

    private String firstName;

    private String lastName;

    private String shopName;

    private LocalDate dateOfBirth;

    private String profileImgUrl;

}
