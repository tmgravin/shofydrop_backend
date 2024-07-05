package com.shofydrop.service;

import com.shofydrop.entity.Orders;
import com.shofydrop.entity.Users;
import jakarta.persistence.criteria.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Orders save(Orders orders);
    Orders update(Orders orders, Long id);
    Orders findById(Long id);
    List<Orders> findAll();
    Object delete(Long id);
}
