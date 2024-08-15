package com.msp.shofydrop.products.repository;

import com.msp.shofydrop.products.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderRepo {

    // To save order
    Long save(Order order);

    // Find orders by user ID
    Optional<Object> findByUserId(Long userId);

    // Find Orders by Vendor ID
    Optional<Object> findByVendorId(Long vendorId);

    // Find orders by order status
    List<Order> findByOrderStatus(String orderStatus);

    // Find orders by order status and payment status
    List<Order> findByOrderStatusAndPaymentStatus(String orderStatus, String paymentStatus);

}
