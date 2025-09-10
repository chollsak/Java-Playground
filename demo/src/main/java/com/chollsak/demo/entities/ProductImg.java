package com.chollsak.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_img")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "imgPath", nullable = false)
    private String imgPath;


    public ProductImg(Long productId, String imgPath) {
        this.productId = productId;
        this.imgPath = imgPath;
    }
}