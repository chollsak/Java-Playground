package com.chollsak.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "shopname")
    private String shopName;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    private int points;

    @Column(name = "imgpath")
    private String imgPath;

    @Column(name = "user_id")
    private UUID userId;


    public Customer(String firstName, String lastName, String shopName,
                    LocalDate dateOfBirth, int points, UUID userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shopName = shopName;
        this.dateOfBirth = dateOfBirth;
        this.points = points;
        this.userId = userId;
    }
}