package com.shofydrop.controller;

import com.shofydrop.dto.ResponseDto;
import com.shofydrop.entity.Product;
import com.shofydrop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Get All Products");
        responseDto.setData(productService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Get Product By Id");
        responseDto.setData(productService.findById(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Add Product");
        responseDto.setData(productService.save(product));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Delete Product");
        responseDto.setData(productService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.OK);
        responseDto.setMessage("Update Product");
        responseDto.setData(productService.update(product, id));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
