package com.msp.shofydrop.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
public class FileUtils {

    // Logger instance for logging information and errors
    // Directory where documents will be stored
    private static final String DOCUMENTS = "./src/main/resources/static/documents";


    //     * Saves a file to the specified directory.
    public void saveFile(MultipartFile file, String fileName) throws IOException {
        Path uploads = Paths.get(DOCUMENTS); // Path to the documents directory
        if (!Files.exists(uploads)) {
            Files.createDirectories(uploads); // Create the directory if it doesn't exist
        }
        Files.copy(file.getInputStream(), uploads.resolve(fileName)); // Copy the file to the target location
    }


    //     * Generates a unique filename using a truncated UUID and the original file extension.
    public String generateFileName(MultipartFile file) {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename()); // Clean the original filename
        String extension = getFileExtension(originalFilename); // Extract the file extension
        String uuidPart = UUID.randomUUID().toString().substring(0, 20); // Generate a truncated UUID
        String generatedFileName = uuidPart + "." + extension; // Concatenate UUID part with the extension
        return generatedFileName;
    }


    //     * Deletes a file if it exists in the directory.
    public void deleteFileIfExists(String fileName) throws IOException {
        Path filePath = Paths.get(DOCUMENTS).resolve(fileName); // Path to the file
        if (Files.exists(filePath)) {
            Files.delete(filePath); // Delete the file if it exists
            log.info("Deleted file: " + fileName); // Log the deletion
        } else {
            log.error("File not found for deletion: " + fileName); // Log a warning if the file doesn't exist
        }
    }


    //     * Extracts the file extension from the filename.
    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1); // Extract and return the extension
    }

    //    Cleanup multipart temp files
    public void cleanupMultipartFile(MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            try {
                File tempFile = multipartFile.getResource().getFile();
                if (tempFile.exists()) {
                    tempFile.delete();
                    log.info("Deleted temporary file: " + tempFile.getAbsolutePath());
                }
            } catch (IOException e) {
                log.error("Failed to delete temporary file", e);
            }
        }
    }
}
