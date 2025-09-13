package com.chollsak.demo.repositories;

import com.chollsak.demo.entities.Customer;
import com.chollsak.demo.entities.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductImgRepository extends JpaRepository<ProductImg, UUID> {
    List<ProductImg> findByProductId(UUID productId);
}
