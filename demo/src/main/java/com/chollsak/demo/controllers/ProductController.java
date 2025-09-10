package com.chollsak.demo.controllers;

import com.chollsak.demo.dtos.ProductDTO;
import com.chollsak.demo.dtos.ProductRequestDTO;
import com.chollsak.demo.dtos.ProductResponseDTO;
import com.chollsak.demo.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductService productService;
    @Autowired
    public ObjectMapper objectMapper;

    @PostMapping
    public ProductDTO addProduct(
            @RequestParam("product") String productJson,
            @RequestParam(value = "file", required = false) List<MultipartFile> imageFiles) {

        try {
            ProductRequestDTO productRequestDTO = objectMapper.readValue(productJson, ProductRequestDTO.class);

            ProductDTO createdProduct = productService.addProduct(productRequestDTO, imageFiles);
            return createdProduct;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.toString());
        }
    }

    @GetMapping
    public ResponseEntity<ProductResponseDTO> getAllProducts() {
        try {
            List<ProductDTO> products = productService.getAllProducts();
            ProductResponseDTO response = new ProductResponseDTO(products);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}