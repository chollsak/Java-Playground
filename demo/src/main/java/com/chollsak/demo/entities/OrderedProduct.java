package com.chollsak.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "ordered_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "final_quantity")
    private int finalQuantity;

    public OrderedProduct(UUID productId, UUID orderId, int quantity){
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }
}
