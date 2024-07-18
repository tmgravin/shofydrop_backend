package com.msp.shofydrop.service.impl;

import com.msp.shofydrop.service.DeliveryService;
import com.msp.shofydrop.entity.Delivery;
import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery findById(Long id) {
        return deliveryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Delivery does not exist with id" + id
                )
        );
    }

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery update(Delivery delivery, Long id) {
        boolean isExist = deliveryRepository.existsById(id);
        if (isExist) {
            Delivery isExistingDelivery = deliveryRepository.findById(id).get();
            isExistingDelivery.setStatus(delivery.getStatus());
            isExistingDelivery.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return deliveryRepository.save(isExistingDelivery);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        deliveryRepository.deleteById(id);
    }
}
