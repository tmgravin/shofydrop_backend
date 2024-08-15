package com.msp.shofydrop.products.repository;

import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderRepo {

    // To save order
    Long save(Order order);

    // Find orders by user ID
    List<Order> findByUserId(Long userId);

    // Find Orders by Vendor ID
    List<OrderItems> findByVendorId(Long vendorId);

    // Find orders by order status
    List<Order> findByOrderStatus(String orderStatus);

    // Find orders by order status and payment status
    List<Order> findByOrderStatusAndPaymentStatus(String orderStatus, String paymentStatus);

}
