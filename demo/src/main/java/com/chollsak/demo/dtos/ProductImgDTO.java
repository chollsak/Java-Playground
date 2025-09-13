package com.chollsak.demo.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgDTO {
    public UUID id;
    public UUID productId;
    public String imgPath;

    public ProductImgDTO(UUID productId, String imgPath) {
        this.productId = productId;
        this.imgPath = imgPath;
    }
}
