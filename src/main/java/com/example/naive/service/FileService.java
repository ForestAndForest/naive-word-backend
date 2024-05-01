package com.example.naive.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadImage(MultipartFile file, HttpServletRequest request) throws IOException;

    Object loadImage(String fileName, HttpServletRequest request) throws IOException;

}
