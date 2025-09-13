package com.chollsak.demo.entities;

import com.chollsak.demo.enums.OrderStatus;
import com.chollsak.demo.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_by")
    private UUID orderBy;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "comment")
    private String comment;

//    @Column(name = "order_document_img")
//    private String orderDocumentImgId;

    public Order(UUID orderBy, LocalDateTime orderDate, PaymentType paymentType, String comment){
        this.orderBy = orderBy;
        this.orderDate = orderDate;
        this.paymentType = paymentType;
        this.comment = comment;
    }
}
