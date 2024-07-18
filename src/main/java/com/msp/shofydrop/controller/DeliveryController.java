package com.msp.shofydrop.controller;

import com.msp.shofydrop.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

}
