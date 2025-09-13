package com.chollsak.demo.controllers;

import com.chollsak.demo.dtos.OrderDTO;
import com.chollsak.demo.dtos.OrderRequestDTO;
import com.chollsak.demo.dtos.OrderedProductDTO;
import com.chollsak.demo.enums.OrderStatus;
import com.chollsak.demo.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            OrderDTO order = orderService.orderProducts(orderRequestDTO);
            return ResponseEntity.ok(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable UUID userId) {
        List<OrderDTO> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable UUID orderId) {
        try {
            OrderDTO order = orderService.getOrderById(orderId);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{orderId}/products")
    public ResponseEntity<List<OrderedProductDTO>> getOrderProducts(@PathVariable UUID orderId) {
        List<OrderedProductDTO> products = orderService.getOrderedProducts(orderId);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable UUID orderId,
            @RequestBody OrderStatus status) {
        try {
            OrderDTO order = orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
