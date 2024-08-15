package com.msp.shofydrop.products.repository;

import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemsRepo {
    // To save orderedItems
    Long save(OrderItems orderItems);

    // Find orderedItems by user ID
    Optional<Object> findOrderItemsByUserId(Long userId);

    // Find orderedItems by vendorID
    Optional<Object> findOrderItemsByVendorId(Long vendorId);

    // Find orderedItems by order status
    List<Order> findByOrderStatus(String orderStatus);

    // Find orderedItems by order status and payment status
    List<Order> findByOrderStatusAndPaymentStatus(String orderStatus, String paymentStatus);

}
