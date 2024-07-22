package com.msp.shofydrop.authentication.serviceImpl;


import com.msp.shofydrop.authentication.entity.UserImages;
import com.msp.shofydrop.authentication.repository.UserImageRepogitory;
import com.msp.shofydrop.authentication.repository.UserRepository;
import com.msp.shofydrop.authentication.service.UserImageService;
import com.msp.shofydrop.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserImageServiceImpl implements UserImageService {
    @Autowired
    private UserImageRepogitory userImageRepogitory;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private FileUtils fileUtils;

    private static final Logger log = LoggerFactory.getLogger(UserImageServiceImpl.class);

    @Override
    public UserImages save(Long userId, UserImages userImages, MultipartFile imageUrl) throws IOException {
////        Users users = usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("The user does not exist into database"));
//
//        if (imageUrl != null) {
//            String fileName = fileUtils.generateFileName(imageUrl); // Generate unique filename
//            userImages.setImageUrl(fileName);
//            fileUtils.saveFile(imageUrl, fileName); // Save front image file
//        }
//
//        userImages.setUsers(users);
//        UserImages userImages1 = userImageRepogitory.save(userImages);
//        log.info("User Image saved Successfully");
        return null;
    }

    @Override
    public UserImages findById(Long id) {
        return null;
    }

    @Override
    public UserImages findByUserId(Long UserId) {
        return null;
    }

    @Override
    public UserImages update(Long id, UserImages userImages) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
