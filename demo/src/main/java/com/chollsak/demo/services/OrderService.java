package com.chollsak.demo.services;

import com.chollsak.demo.dtos.OrderDTO;
import com.chollsak.demo.dtos.OrderRequestDTO;
import com.chollsak.demo.dtos.OrderedProductDTO;
import com.chollsak.demo.dtos.OrderedProductRequestDTO;
import com.chollsak.demo.entities.Order;
import com.chollsak.demo.entities.OrderedProduct;
import com.chollsak.demo.entities.User;
import com.chollsak.demo.enums.OrderStatus;
import com.chollsak.demo.enums.Role;
import com.chollsak.demo.repositories.OrderRepository;
import com.chollsak.demo.repositories.OrderedProductRepository;
import com.chollsak.demo.repositories.ProductRepository;
import com.chollsak.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderDTO orderProducts(OrderRequestDTO orderRequestDTO){
        validateOrderRequest(orderRequestDTO);

        User isUser = userRepository.findById(orderRequestDTO.getOrderBy()).orElse(null);

        if(isUser.equals(null)){
            throw new IllegalArgumentException("User not found");
        }

        if(isUser.getRole().equals(Role.ADMIN)){
            throw new IllegalArgumentException("Admin cannot order");
        }

        Order order = new Order(
                orderRequestDTO.getOrderBy(),
                LocalDateTime.now(),
                orderRequestDTO.getPaymentType(),
                orderRequestDTO.getComment()
                );

        order.setOrderStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);

        List<OrderedProduct> orderedProducts = orderRequestDTO.getProducts().stream()
                .map(productRequest -> new OrderedProduct(
                        productRequest.getProductId(),
                        savedOrder.getId(),
                        productRequest.getQuantity()
                )).collect(Collectors.toList());

        orderedProductRepository.saveAll(orderedProducts);

        return new OrderDTO(savedOrder);
    }

    private void validateOrderRequest(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO.getProducts() == null || orderRequestDTO.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one product");
        }

        for (OrderedProductRequestDTO productRequest : orderRequestDTO.getProducts()) {
            if (!productRepository.existsById(productRequest.getProductId())) {
                throw new IllegalArgumentException("Product not found: " + productRequest.getProductId());
            }

            if (productRequest.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0");
            }
        }
    }

    public List<OrderDTO> getOrdersByUser(UUID userId) {
        List<Order> orders = orderRepository.findByOrderBy(userId);
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return new OrderDTO(order);
    }

    public List<OrderedProductDTO> getOrderedProducts(UUID orderId) {
        List<OrderedProduct> orderedProducts = orderedProductRepository.findByOrderId(orderId);
        return orderedProducts.stream()
                .map(OrderedProductDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);

        return new OrderDTO(updatedOrder);
    }

    @Transactional
    public void updateFinalQuantity(UUID orderedProductId, int finalQuantity) {
        OrderedProduct orderedProduct = orderedProductRepository.findById(orderedProductId)
                .orElseThrow(() -> new RuntimeException("Ordered product not found"));

        orderedProduct.setFinalQuantity(finalQuantity);
        orderedProductRepository.save(orderedProduct);
    }
}
