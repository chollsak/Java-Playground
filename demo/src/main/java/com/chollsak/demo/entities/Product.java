package com.chollsak.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private int price;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private String category;

//    @Column(name = "promotion_type")
//    private String promotionType;
//
//    @Column(name = "promotion_valid_date")
//    private LocalDate promotionValidDate;
//
//    @Column(name = "promotion_expire_date")
//    private LocalDate promotionExpireDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public Product(String name, String description, int price, Integer point,
                   String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.point = point;
        this.category = category;
        this.isActive = true;
    }

//    public Product(String name, String description, BigDecimal price, Integer point,
//                   String category, String promotionType, LocalDate promotionValidDate,
//                   LocalDate promotionExpireDate) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.point = point;
//        this.category = category;
//        this.promotionType = promotionType;
//        this.promotionValidDate = promotionValidDate;
//        this.promotionExpireDate = promotionExpireDate;
//        this.isActive = true;
//    }
}