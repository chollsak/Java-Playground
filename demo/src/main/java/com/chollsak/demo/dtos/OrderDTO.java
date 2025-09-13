package com.chollsak.demo.dtos;

import com.chollsak.demo.entities.Order;
import com.chollsak.demo.enums.OrderStatus;
import com.chollsak.demo.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    public UUID id;
    public UUID orderBy;
    public LocalDateTime orderDate;
    public PaymentType paymentType;
    public OrderStatus status;
    public String comment;
    private List<ProductImgDTO> productImages = new ArrayList<>();

    public OrderDTO(Order order){
        this.id = order.getId();
        this.orderBy = order.getOrderBy();
        this.orderDate = order.getOrderDate();
        this.paymentType = order.getPaymentType();
        this.status = order.getOrderStatus();
        this.comment = order.getComment();
    }
}
