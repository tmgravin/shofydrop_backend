package com.msp.shofydrop.controller;

import com.msp.shofydrop.dto.ResponseDto;
import com.msp.shofydrop.entity.Orders;
import com.msp.shofydrop.service.OrderService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody Orders orders) {
        log.info("Order Added");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orders));
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id) {
        ResponseDto response = new ResponseDto();
        log.info("Getting Order: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody Orders orders) {
        log.info("Updating Order: " + orders.toString());
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(orders));
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
        log.info("Deleting Order: " + id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
