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

//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
//        log.info("Getting Product with ID: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
//        log.info("Saving Product: " + product.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
//        log.info("Deleting Product with ID: " + id);
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
//        log.info("Updating Product: " + product.toString());
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(product));
    }
}
