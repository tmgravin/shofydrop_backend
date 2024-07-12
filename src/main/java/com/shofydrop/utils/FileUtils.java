package com.shofydrop.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtils {

    private static final String UPLOADS = "./src/main/resources/static/documents";

    public void saveFile(MultipartFile file, String filename) throws IOException {
        Path uploads = Paths.get(UPLOADS);
        if (!Files.exists(uploads)) {
            Files.createDirectories(uploads);
        }
        Files.copy(file.getInputStream(), uploads.resolve(filename));
    }

    public String generateFileName(MultipartFile file) {
        return StringUtils.cleanPath(file.getOriginalFilename());
    }
}
