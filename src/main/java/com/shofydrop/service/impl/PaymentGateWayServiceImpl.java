package com.shofydrop.service.impl;

import com.shofydrop.entity.PaymentGateWay;
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
    /**
     * @return 
     */
    @Override
    public List<PaymentGateWay> findAll() {
        return paymentGateWayRepository.findAll();
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public PaymentGateWay findById(Long id) {
        return paymentGateWayRepository.findById(id).
                orElseThrow(()->new
                        ResourceNotFoundException("payment gateway does not exist with id"+id)
                );
    }

    /**
     * @param paymentGateWay 
     * @return
     */
    @Override
    public PaymentGateWay save(PaymentGateWay paymentGateWay) {
        return paymentGateWayRepository.save(paymentGateWay);
    }

    /**
     * @param paymentGateWay 
     * @param id
     * @return
     */
    @Override
    public PaymentGateWay update(PaymentGateWay paymentGateWay, Long id) {
        boolean isExist=paymentGateWayRepository.existsById(id);
        if (isExist){
            PaymentGateWay isExistingPaymentGateWay=paymentGateWayRepository.findById(id).get();
            isExistingPaymentGateWay.setPaymentMethod(paymentGateWay.getPaymentMethod());
            isExistingPaymentGateWay.setQrCode(paymentGateWay.getQrCode());
            return paymentGateWayRepository.save(paymentGateWay);
        }
        return null;
    }

    /**
     * @param id 
     */
    @Override
    public void delete(Long id) {
      paymentGateWayRepository.deleteById(id);
    }
}
