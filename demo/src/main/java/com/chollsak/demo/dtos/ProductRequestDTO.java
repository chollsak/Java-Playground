package com.chollsak.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private String name;
    private String description;
    private int price;
    private Integer point;
    private String category;
    private String promotionType;
    private LocalDate promotionValidDate;
    private LocalDate promotionExpireDate;
    private Boolean isActive;
    private List<String> imagePaths;
}