package com.uptc.bb.internselection.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private static final String STORAGE_DIR = "uploads/";

    public FileStorageService() throws IOException {
        Files.createDirectories(Paths.get(STORAGE_DIR)); 
    }

    public String storeCV(MultipartFile file, String internName) throws IOException {
        if (!file.getOriginalFilename().endsWith(".pdf")) {
            throw new IllegalArgumentException("Solo se permiten archivos PDF");
        }

        String filename = internName.replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + ".pdf";
        Path destination = Paths.get(STORAGE_DIR).resolve(filename);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return destination.toString();
    }

    public byte[] getCV(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }
}

