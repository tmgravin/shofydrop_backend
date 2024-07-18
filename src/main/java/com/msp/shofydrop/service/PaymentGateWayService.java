package com.msp.shofydrop.service;

import com.msp.shofydrop.entity.PaymentGateway;

import java.util.List;

public interface PaymentGateWayService {
    List<PaymentGateway> findAll();

    PaymentGateway findById(Long id);

    PaymentGateway save(PaymentGateway paymentGateWay);

    PaymentGateway update(PaymentGateway paymentGateWay);

    void delete(Long id);
}
