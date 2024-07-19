package com.msp.shofydrop.stores.repository;

import com.msp.shofydrop.stores.entity.StoreContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreContactRepository extends JpaRepository<StoreContact, Long> {
    List<StoreContact> findAll();
}
