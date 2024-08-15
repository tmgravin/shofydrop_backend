package com.msp.shofydrop.products.controller;

import com.msp.shofydrop.products.Dto.OrderDto;
import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import com.msp.shofydrop.products.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/")
    public ResponseEntity<?> saveOrderWithItems(@RequestBody Order order) {
        log.info("Inside saveOrderWithItems method of OrderController(products)");
        Long createdOrder = orderService.saveOrderWithItems(order);
        return ResponseEntity.ok(createdOrder);
    }

    @DeleteMapping("/items/{orderItemId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long orderItemId) {
        log.info("Inside deleteOrderItem method of OrderController(products)");
        orderService.deleteOrderItem(orderItemId);
        return ResponseEntity.ok("OrderItem deleted successfully");
    }
}

