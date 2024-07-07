package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Orders;
import com.shofydrop.service.OrderService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<?> addOrder(@RequestBody Orders orders){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully order added");
        responseDto.setData(orderService.save(orders));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully order found");
        responseDto.setData(orderService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody Orders orders) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("successfully order updated");
        responseDto.setData(orderService.update(orders));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
