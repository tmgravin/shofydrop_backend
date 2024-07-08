package com.shofydrop.service;

import com.shofydrop.entity.PaymentGateway;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PaymentGateWayService {
    List<PaymentGateway> findAll();
    PaymentGateway findById(Long id);
    PaymentGateway save(PaymentGateway paymentGateWay);
    PaymentGateway update(PaymentGateway paymentGateWay);
    void delete(Long id);
}
