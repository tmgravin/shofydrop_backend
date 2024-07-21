package com.msp.shofydrop.authentication.repository;

import com.msp.shofydrop.authentication.entity.UserImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepogitory extends JpaRepository<UserImages, Long> {
}
