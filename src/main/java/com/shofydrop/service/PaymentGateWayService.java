package com.shofydrop.service;

import com.shofydrop.entity.PaymentGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentGateWayService {
    public List<PaymentGateway> findAll();

    public PaymentGateway findById(Long id);

    public PaymentGateway save(PaymentGateway paymentGateWay);

    public PaymentGateway update(PaymentGateway paymentGateWay, Long id);

    public void delete(Long id);
}
