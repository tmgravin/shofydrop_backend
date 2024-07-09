package com.shofydrop.service;

import com.shofydrop.entity.Orders;

import java.util.List;

public interface OrderService {
    Orders save(Orders orders);

    Orders update(Orders orders);

    Orders findById(Long id);

    List<Orders> findAll();

    Void delete(Long id);
}
