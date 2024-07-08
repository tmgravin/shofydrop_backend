package com.shofydrop.service.impl;

import com.shofydrop.entity.Orders;
import com.shofydrop.entity.Users;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.OrdersRepository;
import com.shofydrop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Orders update(Orders orders) {
        return ordersRepository.save(orders);
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("order does not exist with this id " + id));
    }

    @Override
    public Void delete(Long id) {
        try{
            ordersRepository.deleteById(id);
            return null;
        }catch (Exception e){
            throw new RuntimeException("Internal Server Error" + id + e.getMessage());
        }
    }
}
