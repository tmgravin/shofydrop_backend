package com.msp.shofydrop.products.service;

import com.msp.shofydrop.products.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    Long saveOrderWithItems(Order order);

    Optional<Order> findOrderById(Long id);

    List<Order> findOrdersWithItemsByUserOrVendorId(Long userId, Long vendorId);

    Optional<Object> findOrderByUserId(Long id);

    Optional<Object> findOrderByVendorId(Long id);

    Optional<Object> findOrderItemsByUserId(Long userId);

    Optional<Object> findOrderItemsByVendorId(Long vendorId);

    void deleteById(Long id);

    void deleteOrderItem(Long orderItemId);
}
