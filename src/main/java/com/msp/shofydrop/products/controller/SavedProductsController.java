package com.msp.shofydrop.products.controller;

import com.msp.shofydrop.products.Entity.SavedProduct;
import com.msp.shofydrop.products.service.SavedProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/saved-products/")
public class SavedProductsController {

    @Autowired
    private SavedProductService savedProductService;

    private static final Logger log = LoggerFactory.getLogger(SavedProductsController.class);

    @PostMapping("/save")
    public ResponseEntity<?> addSavedProduct(@RequestBody SavedProduct savedProduct){
        log.info("Inside addSavedProduct method of savedProductController(products)");
        Long savedProductId = savedProductService.addSavedProducts(savedProduct);
        return new ResponseEntity<>(savedProductId, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getByUserId(@RequestParam(value = "id", required = false) Long id){
        log.info("Inside getByUserId method savedProductController(products)");
        try{
            return ResponseEntity.ok().body(savedProductService.findByUserId(id));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }
}
