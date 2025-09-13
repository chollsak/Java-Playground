package com.chollsak.demo.dtos;

import com.chollsak.demo.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    public UUID id;
    public String name;
    public String description;
    public int price;
    public int point;
    public String category;
    public Boolean isActive;
//    public String promotionType;
//    public String promotion_validDate;
//    public String expireDate;
    private List<ProductImgDTO> productImages = new ArrayList<>();

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.point = product.getPoint();
        this.category = product.getCategory();
        this.isActive = product.getIsActive();
        this.productImages = new ArrayList<>();
    }

    public ProductDTO(Product product, List<ProductImgDTO> productImages) {
        this(product);
        this.productImages = productImages != null ? productImages : new ArrayList<>();
    }
}
