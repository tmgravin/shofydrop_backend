package com.msp.shofydrop.repository;

import com.msp.shofydrop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
