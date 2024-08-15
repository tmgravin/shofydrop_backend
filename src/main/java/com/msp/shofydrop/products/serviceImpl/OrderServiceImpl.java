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
    public Optional<Order> findById(Long id) {
       return null;
    }

    @Override
    @Transactional
    public List<Order> findByUserId(Long id) {
        return null;
    }

    @Override
    @Transactional
    public List<Order> findByVendorId(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // Implementation
    }

    @Override
    @Transactional
    public Long saveOrderItem(OrderItems orderItem) {
        return orderItemsRepo.save(orderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(Long orderItemId) {
        // Implementation
    }
}
