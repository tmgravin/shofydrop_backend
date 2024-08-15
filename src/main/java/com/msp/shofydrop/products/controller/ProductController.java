package com.msp.shofydrop.products.controller;

import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.products.Entity.Image;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/products/product")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @Value("${image.storage.path}")
    private String path;

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> saveProduct(
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stock") Long stock,
            //@RequestParam("categoryId") Long categoryId,
            //@RequestParam("subcategoryId") Long subcategoryId,
            @RequestParam("vendorId") Long vendorId,
            @RequestPart("images") List<MultipartFile> images) {

        try {
            Products products = new Products();
            products.setProductName(productName);
            products.setDescription(description);
            products.setPrice(price);
            products.setStock(stock);
            //products.setCategoryId(categoryId);
            //products.setSubcategoryId(subcategoryId);
            products.setVendorId(vendorId);

            Long savedProductId = productsService.addProducts(products, images);
            return ResponseEntity.ok().body(savedProductId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/pro")
    public ResponseEntity<?> getProducts(@RequestParam(name = "id",required = false) Long id) {
        log.info("Inside getProducts method of ProductController(products)");
        try {
         return ResponseEntity.ok().body(productsService.getProducts(id));
       }catch (Exception e){
           return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
       }
    }

   // method to serve file
    @GetMapping("/images/{imageName}")
    public void getImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream content = this.productsService.getImagesContent(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(content,response.getOutputStream());
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
