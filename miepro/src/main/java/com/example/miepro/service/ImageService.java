package com.example.miepro.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public String saveImage(MultipartFile imageFile);
}
