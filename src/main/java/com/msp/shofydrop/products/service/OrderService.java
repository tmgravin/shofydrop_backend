package com.msp.shofydrop.products.service;

import com.msp.shofydrop.products.Dto.OrderDto;
import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    Long saveOrderWithItems(Order order);

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long id);

    List<Order> findByVendorId(Long id);

    void deleteById(Long id);

    // Add these methods
    Long saveOrderItem(OrderItems orderItem);

    void deleteOrderItem(Long orderItemId);
}
