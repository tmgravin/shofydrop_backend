package com.shofydrop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private static final String UPLOADS = "./src/main/resources/static/documents";

    public void saveFile(MultipartFile file, String fileName) throws IOException {
        Path uploads = Paths.get(UPLOADS);
        if (!Files.exists(uploads)) {
            Files.createDirectories(uploads);
        }
        Files.copy(file.getInputStream(), uploads.resolve(fileName));
    }

    public String generateFileName(MultipartFile file) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = getFileExtension(originalFilename);
        String uuidPart = UUID.randomUUID().toString().substring(0, 20);  // Truncate UUID to first 20 characters
        String generatedFileName = uuidPart + "." + extension;
        return generatedFileName;
    }

    //    Remove Old images from folder
    public void deleteFileIfExists(String fileName) throws IOException {
        Path filePath = Paths.get(UPLOADS).resolve(fileName);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
            log.info("Deleted file: " + fileName);
        } else {
            log.warn("File not found for deletion: " + fileName);
        }
    }


    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
