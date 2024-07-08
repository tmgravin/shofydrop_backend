package com.shofydrop.service.impl;

import com.shofydrop.entity.PaymentGateway;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.PaymentGateWayRepository;
import com.shofydrop.service.PaymentGateWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentGateWayServiceImpl implements PaymentGateWayService {
    @Autowired
    private PaymentGateWayRepository paymentGateWayRepository;

    @Override
    public List<PaymentGateway> findAll() {
        return paymentGateWayRepository.findAll();
    }


    @Override
    public PaymentGateway findById(Long id) {
        return paymentGateWayRepository.findById(id).
                orElseThrow(()->new
                        ResourceNotFoundException("payment gateway does not exist with id"+id)
                );
    }


    @Override
    public PaymentGateway save(PaymentGateway paymentGateWay) {
        return paymentGateWayRepository.save(paymentGateWay);
    }

    @Override
    public PaymentGateway update(PaymentGateway paymentGateWay) {
        return paymentGateWayRepository.save(paymentGateWay);
    }

    @Override
    public void delete(Long id) {
      paymentGateWayRepository.deleteById(id);
    }
}
