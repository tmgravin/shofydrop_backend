package com.msp.shofydrop.stores.repository;


import com.msp.shofydrop.stores.entity.StoreContact;

import java.util.List;

public interface StoreContactRepository {
    List<StoreContact> findAll();
}
