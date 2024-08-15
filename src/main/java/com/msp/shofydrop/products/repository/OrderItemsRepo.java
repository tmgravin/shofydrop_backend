package com.msp.shofydrop.products.repository;

import com.msp.shofydrop.products.Dto.OrderDto;
import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepo {
    // To save order
    Long save(OrderItems orderItems);

    // Find orders by user ID
    List<Order> findByUserId(int userId);

    // Find orders by order status
    List<Order> findByOrderStatus(String orderStatus);

    // Find orders by order status and payment status
    List<Order> findByOrderStatusAndPaymentStatus(String orderStatus, String paymentStatus);

}
