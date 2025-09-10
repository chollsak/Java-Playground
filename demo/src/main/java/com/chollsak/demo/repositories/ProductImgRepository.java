package com.chollsak.demo.repositories;

import com.chollsak.demo.entities.Customer;
import com.chollsak.demo.entities.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {
    List<ProductImg> findByProductId(Long productId);
}
