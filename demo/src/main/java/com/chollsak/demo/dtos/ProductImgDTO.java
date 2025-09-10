package com.chollsak.demo.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgDTO {
    public Long id;
    public Long productId;
    public String imgPath;

    public ProductImgDTO(Long productId, String imgPath) {
        this.productId = productId;
        this.imgPath = imgPath;
    }
}
