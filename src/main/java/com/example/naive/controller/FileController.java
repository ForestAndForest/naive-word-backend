package com.example.naive.controller;

import com.example.naive.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@CrossOrigin
@RestController
public class FileController {


    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PutMapping("/file/img")
    public Object upload(@RequestAttribute MultipartFile file, HttpServletRequest request) throws IOException {
        return fileService.uploadImage(file, request);
    }

    @PutMapping("/file/imgs")
    public Object upload(@RequestAttribute MultipartFile[] files, HttpServletRequest request) throws IOException {
        return Arrays.stream(files).map(file -> {
            try {
                return fileService.uploadImage(file, request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @GetMapping("/img/{fileName}")
    public Object loadImage(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        return fileService.loadImage(fileName, request);
    }
}
