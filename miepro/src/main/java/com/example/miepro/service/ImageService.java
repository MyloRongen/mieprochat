package com.example.miepro.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String saveImageAndReturnUrl(MultipartFile imageFile);
}
