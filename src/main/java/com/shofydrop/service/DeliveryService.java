package com.shofydrop.service;

import com.shofydrop.entity.Delivery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface DeliveryService {
    public List<Delivery> findAll();
    public Delivery findById(Long id);
    public Delivery save( Delivery delivery);
    public Delivery update(Delivery delivery ,Long id);
    public void delete(Long id);
}
