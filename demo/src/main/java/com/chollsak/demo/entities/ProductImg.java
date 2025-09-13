package com.chollsak.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "product_img")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "imgPath", nullable = false)
    private String imgPath;


    public ProductImg(UUID productId, String imgPath) {
        this.productId = productId;
        this.imgPath = imgPath;
    }
}