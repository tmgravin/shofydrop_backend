package com.shofydrop.service;

import com.shofydrop.entity.PaymentGateWay;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentGateWayService {
    public List<PaymentGateWay> findAll();

    public PaymentGateWay findById(Long id);

    public PaymentGateWay save(PaymentGateWay paymentGateWay);

    public PaymentGateWay update(PaymentGateWay paymentGateWay, Long id);

    public void delete(Long id);
}
