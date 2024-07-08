package com.shofydrop.repository;

import com.shofydrop.entity.StoreImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImages,Long> {

}
