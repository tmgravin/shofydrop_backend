package com.msp.shofydrop.products.serviceImpl;

import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import com.msp.shofydrop.products.repository.OrderItemsRepo;
import com.msp.shofydrop.products.repository.OrderRepo;
import com.msp.shofydrop.products.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemsRepo orderItemsRepo;

    @Override
    @Transactional
    public Long saveOrderWithItems(Order order) {
        Long orderId = orderRepo.save(order);

        for (OrderItems item : order.getOrderItems()) {
            item.setOrderId(orderId);
            orderItemsRepo.save(item);
        }
        return orderId;
    }

    @Override
    @Transactional
    public Optional<Order> findOrderById(Long id) {
       return null;
    }

    @Override
    public List<Order> findOrdersWithItemsByUserOrVendorId(Long userId, Long vendorId) {
    }

    @Override
    @Transactional
    public Optional<Object> findOrderByUserId(Long id) {
            return orderRepo.findByUserId(id);
    }

    @Override
    @Transactional
    public Optional<Object> findOrderByVendorId(Long id) {
        return orderRepo.findByVendorId(id);
    }

    @Override
    @Transactional
    public Optional<Object> findOrderItemsByUserId(Long userId) {
        return orderItemsRepo.findOrderItemsByUserId(userId);
    }

    @Override
    public Optional<Object> findOrderItemsByVendorId(Long vendorId) {
        return orderItemsRepo.findOrderItemsByVendorId(vendorId);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // Implementation
    }

    @Override
    @Transactional
    public void deleteOrderItem(Long orderItemId) {
        // Implementation
    }
}
