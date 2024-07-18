package com.shofydrop.service;

import com.shofydrop.entity.UserImages;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserImageService {
    UserImages save(Long userId, UserImages userImages, MultipartFile imageUrl) throws IOException;

    UserImages findById(Long id);

    UserImages findByUserId(Long UserId);

    UserImages update(Long id, UserImages userImages);

    void delete(Long id);
}
