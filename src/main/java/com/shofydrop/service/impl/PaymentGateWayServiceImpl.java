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
    /**
     * @return 
     */
    @Override
    public List<PaymentGateway> findAll() {
        return paymentGateWayRepository.findAll();
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public PaymentGateway findById(Long id) {
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
    public PaymentGateway save(PaymentGateway paymentGateWay) {
        return paymentGateWayRepository.save(paymentGateWay);
    }

    /**
     * @param paymentGateWay 
     * @param id
     * @return
     */
    @Override
    public PaymentGateway update(PaymentGateway paymentGateWay, Long id) {
        boolean isExist=paymentGateWayRepository.existsById(id);
        if (isExist){
            PaymentGateway isExistingPaymentGateway =paymentGateWayRepository.findById(id).get();
            isExistingPaymentGateway.setPaymentMethod(paymentGateWay.getPaymentMethod());
            isExistingPaymentGateway.setQrCode(paymentGateWay.getQrCode());
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
