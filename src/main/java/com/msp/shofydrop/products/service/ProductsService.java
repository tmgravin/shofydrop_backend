package com.msp.shofydrop.products.service;

import com.msp.shofydrop.products.Entity.Products;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


public interface ProductsService {

    Long addProducts(Products products, List<MultipartFile> images) throws Exception;

    List<Products> getProducts(Long id);

    InputStream getImagesContent(String path, String fileName) throws FileNotFoundException;

    List<Products> findProductsByName(String name);

    void deleteProductById(Long productId);

}
