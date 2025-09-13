package com.chollsak.demo.dtos;

import com.chollsak.demo.entities.OrderedProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProductDTO {
    public UUID id;
    public UUID productId;
    public UUID orderId;
    public int quantity;
    public int finalQuantity;

    public OrderedProductDTO(OrderedProduct orderedProduct){
        this.id = orderedProduct.getId();
        this.productId = orderedProduct.getProductId();
        this.orderId = orderedProduct.getOrderId();
        this.quantity = orderedProduct.getQuantity();
        this.finalQuantity = orderedProduct.getFinalQuantity();
    }
}
