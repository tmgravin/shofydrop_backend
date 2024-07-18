package com.shofydrop.repository;

import com.shofydrop.entity.UserImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepogitory extends JpaRepository<UserImages, Long> {
}
