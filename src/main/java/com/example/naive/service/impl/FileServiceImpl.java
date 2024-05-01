package com.example.naive.service.impl;

import com.example.naive.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String uploadPath;

    @SuppressWarnings("all")
    @Override
    public String uploadImage(MultipartFile multipartFile, HttpServletRequest request) throws IOException {

        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String originalFileName = Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")[1];
        String newFileName = UUID.randomUUID() + "." + originalFileName;

        File newFile = new File(uploadPath + "/" + newFileName);

        multipartFile.transferTo(newFile);
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img/" + newFileName;
    }

    @Override
    public Object loadImage(String fileName, HttpServletRequest request) throws IOException {
        String suffix = fileName.split("\\.")[1];
        File file = new File(uploadPath + "/" + fileName);
        HttpHeaders headers = new HttpHeaders();
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));
        headers.setContentType(getMediaType(suffix));
        return new ResponseEntity<>(resource, headers, 200);
    }

    private MediaType getMediaType(String suffix) {
        return switch (suffix) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }
}
