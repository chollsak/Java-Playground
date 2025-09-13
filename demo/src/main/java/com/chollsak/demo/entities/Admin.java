package com.chollsak.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "user_id")
    private UUID userId;


    public Admin(String firstName, String lastName, UUID userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }
}