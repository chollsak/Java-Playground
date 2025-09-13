package com.chollsak.demo.repositories;

import com.chollsak.demo.entities.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, UUID> {
    List<OrderedProduct> findByOrderId(UUID orderId);
    List<OrderedProduct> findByProductId(UUID productId);
}
