package com.msp.shofydrop.products.controller;

import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/product")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    @PostMapping("/")
    public ResponseEntity<?> saveProduct(@RequestBody Products products)
    {
        log.info("Inside saveProduct method of ProductController(products)");
        try
        {
            return ResponseEntity.ok().body(productsService.addProducts(products));
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getProducts(@RequestParam(name = "id",required = false) Long id) {
        log.info("Inside getProducts method of ProductController(products)");
        try {
         return ResponseEntity.ok().body(productsService.getProducts(id));
       }catch (Exception e){
           return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
       }
    }

    @DeleteMapping("/deleteProducts/{id}")
    public ResponseEntity<String> deleteProducts(@PathVariable Long id) {
        List<Products> product = productsService.getProducts(id);

        if (product == null) {
            throw new ResourceNotFoundException("Product with ID " + id + " not found");
        }
        productsService.deleteProductById(id);
        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
    }

}
