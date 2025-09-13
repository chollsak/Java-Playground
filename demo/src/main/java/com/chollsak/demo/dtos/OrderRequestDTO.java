package com.chollsak.demo.dtos;

import com.chollsak.demo.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private UUID orderBy;
    private PaymentType paymentType;
    private String comment;
    private List<OrderedProductRequestDTO> products;
}
