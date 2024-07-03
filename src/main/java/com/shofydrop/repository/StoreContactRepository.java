package com.shofydrop.repository;

import com.shofydrop.entity.StoreContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreContactRepository extends JpaRepository<StoreContact,Long> {
}