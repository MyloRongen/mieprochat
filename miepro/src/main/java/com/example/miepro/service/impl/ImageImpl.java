package com.example.miepro.service.impl;

import com.example.miepro.exception.InvalidImageException;
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
import java.util.Objects;

@Service
public class ImageImpl implements ImageService {

    private final ResourceLoader resourceLoader;

    public ImageImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String saveImageAndReturnUrl(MultipartFile imageFile) {
        try {
            String imageName = generateImageName(Objects.requireNonNull(imageFile.getOriginalFilename()));
            String uploadDirectory = getUploadDirectory();

            Path imagePath = Path.of(uploadDirectory, imageName);
            Files.copy(imageFile.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            return imageName;
        } catch (IOException e) {
            throw new InvalidImageException(e.getMessage());
        }
    }

    private String generateImageName(String originalFilename) {
        String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm ssSSS"));
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        return fileNameWithoutExtension + formattedDateTime + fileExtension;
    }

    private String getUploadDirectory() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/images/");
        return resource.getFile().getAbsolutePath();
    }
}
