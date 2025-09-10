package com.chollsak.demo.services;

import com.chollsak.demo.dtos.ProductDTO;
import com.chollsak.demo.dtos.ProductImgDTO;
import com.chollsak.demo.dtos.ProductRequestDTO;
import com.chollsak.demo.entities.Product;
import com.chollsak.demo.entities.ProductImg;
import com.chollsak.demo.repositories.ProductRepository;
import com.chollsak.demo.repositories.ProductImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImgRepository productImgRepository; // Missing repository

    @Autowired
    private FileStorageService fileStorageService;

    @Transactional
    public ProductDTO addProduct(ProductRequestDTO productRequestDTO, List<MultipartFile> imageFiles) {
        try {
            Product product = new Product();
            product.setName(productRequestDTO.getName());
            product.setDescription(productRequestDTO.getDescription());
            product.setPrice(productRequestDTO.getPrice());
            product.setPoint(productRequestDTO.getPoint());
            product.setCategory(productRequestDTO.getCategory());
            product.setIsActive(productRequestDTO.getIsActive() != null ?
                    productRequestDTO.getIsActive() : true);

            Product savedProduct = productRepository.save(product);

            List<ProductImg> productImages = new ArrayList<>();
            List<String> uploadedImagePaths = new ArrayList<>();

            try {
                // Handle image file uploads first (if provided)
                if (imageFiles != null && !imageFiles.isEmpty()) {
                    for (MultipartFile imageFile : imageFiles) {
                        if (!imageFile.isEmpty()) {
                            String imagePath = fileStorageService.uploadProductImage(imageFile, savedProduct.getId());
                            uploadedImagePaths.add(imagePath);

                            ProductImg productImg = new ProductImg();
                            productImg.setProductId(savedProduct.getId());
                            productImg.setImgPath(imagePath);

                            ProductImg savedImage = productImgRepository.save(productImg);
                            productImages.add(savedImage);
                        }
                    }
                }

                // Handle pre-existing image paths (if provided)
                if (productRequestDTO.getImagePaths() != null &&
                        !productRequestDTO.getImagePaths().isEmpty()) {

                    for (String imagePath : productRequestDTO.getImagePaths()) {
                        ProductImg productImg = new ProductImg();
                        productImg.setProductId(savedProduct.getId());
                        productImg.setImgPath(imagePath);


                        ProductImg savedImage = productImgRepository.save(productImg);
                        productImages.add(savedImage);
                    }
                }

                return convertToProductDTO(savedProduct, productImages);

            } catch (Exception e) {
                // If image upload fails, clean up uploaded files
                if (!uploadedImagePaths.isEmpty()) {
                    fileStorageService.deleteProductImageFiles(uploadedImagePaths);
                }
                throw e;
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to add product: " + e.getMessage(), e);
        }
    }

    // Overloaded method for backward compatibility (no image files)
    public ProductDTO addProduct(ProductRequestDTO productRequestDTO) {
        return addProduct(productRequestDTO, null);
    }

    private ProductDTO convertToProductDTO(Product product, List<ProductImg> productImages) {
        // Convert ProductImg entities to DTOs
        List<ProductImgDTO> imageDTOs = productImages.stream()
                .map(img -> new ProductImgDTO(img.getId(), img.getProductId(), img.getImgPath()))
                .collect(Collectors.toList());

        return new ProductDTO(product, imageDTOs);
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::getProductWithImages)
                .collect(Collectors.toList());
    }

    // Helper method to get product with its images
    private ProductDTO getProductWithImages(Product product) {
        List<ProductImg> productImages = productImgRepository.findByProductId(product.getId());
        return convertToProductDTO(product, productImages);
    }
}