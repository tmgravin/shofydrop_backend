package com.shofydrop.service.impl;

import com.shofydrop.entity.Orders;
import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.OrdersRepository;
import com.shofydrop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLExceptionTranslatorRegistrar;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders save(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders update(Orders orders, Long id) {
     try{
         Orders order = ordersRepository.findById(id).get();
         order.setTotalPrice(orders.getTotalPrice());
         order.setOrderStatus(orders.getOrderStatus());
         order.setOrderDate(orders.getOrderDate());
         order.setUpdateAt(orders.getUpdateAt());
         order.setUsers(orders.getUsers());
         order.setVendors(orders.getVendors());
         order.setPromoCode(orders.getPromoCode());
         ordersRepository.save(order);
         return order;
     }catch (Exception e){
         throw new RuntimeException("Order not found" + id + e.getMessage());
     }
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("order does not exist with this id " + id));
    }

    @Override
    public Users delete(Long id) {
        return null;
    }
}
