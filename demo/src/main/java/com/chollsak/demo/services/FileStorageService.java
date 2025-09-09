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

    public String uploadProfileImage(MultipartFile file, Long customerId) {
        validateFile(file);

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

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!isValidImageType(file.getContentType())) {
            throw new IllegalArgumentException("Invalid file type. Only images allowed.");
        }

        if (file.getSize() > 5 * 1024 * 1024) {
            throw new IllegalArgumentException("File size exceeds 5MB limit");
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

    // Your existing getFileUrl and deleteFile methods remain the same
}