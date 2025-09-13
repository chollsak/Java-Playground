package com.chollsak.demo.services;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FileStorageService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @PostConstruct
    public void initializeBucket() {
        try {
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!exists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(bucketName).build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize MinIO bucket", e);
        }
    }

    public String uploadProfileImage(MultipartFile file, UUID customerId) {
        validateFile(file, 5); // 5MB limit for profiles

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);
            String objectKey = "profiles/" + customerId + "_" + System.currentTimeMillis() + extension;

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectKey)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return objectKey;

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    // Upload product image with product-specific validation
    public String uploadProductImage(MultipartFile file, UUID productId) {
        validateProductImageFile(file);

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = getFileExtension(originalFilename);
            String objectKey = "products/" + productId + "_" + System.currentTimeMillis() + extension;

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectKey)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return objectKey;

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload product image: " + e.getMessage(), e);
        }
    }

    // Upload multiple product images
    public List<String> uploadProductImages(List<MultipartFile> files, UUID productId) {
        return files.stream()
                .filter(file -> !file.isEmpty())
                .map(file -> uploadProductImage(file, productId))
                .collect(Collectors.toList());
    }

    // Generic upload method for any file with custom object key
    public String uploadFile(MultipartFile file, String objectKey) {
        validateFile(file, 10); // 10MB limit for generic files

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectKey)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return objectKey;

        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file: " + objectKey, e);
        }
    }

    // Method to get file URL for viewing
    public String getFileUrl(String objectKey) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectKey)
                            .expiry(60 * 60 * 24) // 24 hours
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to get file URL: " + objectKey, e);
        }
    }

    // Get product image URL with product-specific error handling
    public String getProductImageUrl(String imagePath) {
        try {
            return getFileUrl(imagePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get product image URL: " + imagePath, e);
        }
    }

    // Validate generic files
    private void validateFile(MultipartFile file, int maxSizeMB) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!isValidImageType(file.getContentType())) {
            throw new IllegalArgumentException("Invalid file type. Only images allowed.");
        }

        if (file.getSize() > maxSizeMB * 1024 * 1024) {
            throw new IllegalArgumentException("File size exceeds " + maxSizeMB + "MB limit");
        }
    }

    // Validate product image files with higher limits
    private void validateProductImageFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Product image file is empty");
        }

        if (!isValidImageType(file.getContentType())) {
            throw new IllegalArgumentException("Invalid file type. Only images allowed for products.");
        }

        // 15MB limit for product images
        if (file.getSize() > 15 * 1024 * 1024) {
            throw new IllegalArgumentException("Product image file size exceeds 15MB limit");
        }
    }

    private String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf("."));
        }
        return ".jpg"; // default extension
    }

    private boolean isValidImageType(String contentType) {
        return contentType != null && (
                contentType.equals("image/jpeg") ||
                        contentType.equals("image/png") ||
                        contentType.equals("image/gif") ||
                        contentType.equals("image/jpg") ||
                        contentType.equals("image/webp")
        );
    }

    public void deleteFile(String objectKey) {
        try {
            if (objectKey != null && !objectKey.isEmpty()) {
                minioClient.removeObject(
                        RemoveObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectKey)
                                .build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file: " + objectKey, e);
        }
    }

    public boolean deleteProfileImage(Long customerId) {
        try {
            // List all objects with the customer's profile prefix
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix("profiles/" + customerId + "_")
                            .build()
            );

            boolean deleted = false;
            for (Result<Item> result : results) {
                Item item = result.get();
                deleteFile(item.objectName());
                deleted = true;
            }

            return deleted;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete profile images for customer: " + customerId, e);
        }
    }

    public boolean deleteProductImages(Long productId) {
        try {
            // List all objects with the product's image prefix
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix("products/" + productId + "_")
                            .build()
            );

            boolean deleted = false;
            for (Result<Item> result : results) {
                Item item = result.get();
                deleteFile(item.objectName());
                deleted = true;
            }

            return deleted;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete product images for product: " + productId, e);
        }
    }

    // Delete specific product image files
    public void deleteProductImageFiles(List<String> imagePaths) {
        try {
            deleteMultipleFiles(imagePaths);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete product image files", e);
        }
    }

    public void deleteMultipleFiles(List<String> objectKeys) {
        try {
            List<DeleteObject> deleteObjects = objectKeys.stream()
                    .filter(key -> key != null && !key.isEmpty())
                    .map(DeleteObject::new)
                    .collect(Collectors.toList());

            if (!deleteObjects.isEmpty()) {
                minioClient.removeObjects(
                        RemoveObjectsArgs.builder()
                                .bucket(bucketName)
                                .objects(deleteObjects)
                                .build()
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete multiple files", e);
        }
    }
}