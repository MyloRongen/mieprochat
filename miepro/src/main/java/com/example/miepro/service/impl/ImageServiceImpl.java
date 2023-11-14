package com.example.miepro.service.impl;

import com.example.miepro.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ImageServiceImpl implements ImageService {
    private final ResourceLoader resourceLoader;

    public ImageServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String saveImage(MultipartFile imageFile) {
        try {
            String imageName = generateImageName(imageFile.getOriginalFilename());
            String uploadDirectory = getUploadDirectory();

            Path imagePath = Path.of(uploadDirectory, imageName);

            Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            return imageName;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return null;
        }
    }

    private String generateImageName(String originalFilename) {
        String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        return fileNameWithoutExtension + formattedDateTime + fileExtension;
    }

    private String getUploadDirectory() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/images/");
        return resource.getFile().getAbsolutePath();
    }

}
