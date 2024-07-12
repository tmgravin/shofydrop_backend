package com.shofydrop.service;

import com.shofydrop.entity.StoreContact;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StoreContactService {
    List<StoreContact> findAll();

    StoreContact findById(Long id);

    StoreContact save(StoreContact storeContact);

    StoreContact update(StoreContact storeContact, Long id);

    void delete(Long id);

}
