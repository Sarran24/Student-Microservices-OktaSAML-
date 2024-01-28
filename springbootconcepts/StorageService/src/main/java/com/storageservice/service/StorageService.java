package com.storageservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.storageservice.entity.ImageData;
import com.storageservice.repository.StorageRepository;
import com.storageservice.util.ImageUtils;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.Optional;
@Transactional
@Service
public class StorageService {

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {
        String imageName = file.getOriginalFilename();
        logger.info("Uploading file: {}", imageName);

        Optional<ImageData> imageDataOptional = repository.findByName(imageName);
        if (imageDataOptional.isPresent()) {
            logger.error("File with the same name already exists: {}", imageName);
            throw new IllegalArgumentException("File with the same name already exists: " + imageName);
        }

        ImageData imageData = ImageData.builder()
                .name(imageName)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build();

        imageData = repository.save(imageData);

        if (imageData.getId() > 0) {
            logger.info("File uploaded successfully: {}", imageName);
            return "File uploaded successfully: " + imageName;
        } else {
            logger.error("Failed to upload file: {}", imageName);
            return "Failed to upload file: " + imageName;
        }
    }


    @Transactional
    public byte[] downloadImage(String fileName){
        logger.info("Downloading file: {}", fileName);

        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());

        logger.info("File downloaded successfully: {}", fileName);
        return images;
    }
}
