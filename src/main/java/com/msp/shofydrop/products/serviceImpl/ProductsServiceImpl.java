package com.msp.shofydrop.products.serviceImpl;

import com.msp.shofydrop.exception.ResourceNotFoundException;
import com.msp.shofydrop.products.Entity.Image;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.repository.ProductsRepo;
import com.msp.shofydrop.products.service.ProductsService;
import com.msp.shofydrop.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private FileUtils fileUtils;

    @Override
    @Transactional
    public Long addProducts(Products products, List<MultipartFile> imageFile) throws Exception
    {
        Long savedProductId = productsRepo.saveProduct(products);
        Image image = new Image();
        String fileName = null;
        for (MultipartFile File : imageFile)
        {
            fileName = fileUtils.generateFileName(File);
            fileUtils.saveFile(File,fileName);
            image.setImages(fileName);
            image.setProductId(savedProductId);
            productsRepo.saveImage(image);
        }
        return savedProductId;
    }

    @Override
    @Transactional
    public List<Products> getProducts(Long id) {
        return productsRepo.getProducts(id);
    }

    @Override
    @Transactional
    public InputStream getImagesContent(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        // db logic to return input Stream
        return inputStream;
    }

    @Override
    @Transactional
    public List<Products> findProductsByName(String name) {
        return productsRepo.findBynameContaining(name);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        List<Products> product = productsRepo.getProducts(productId);

        if (product != null && !product.isEmpty()) {
            productsRepo.deleteProductProductById(productId);
        } else {
            throw new ResourceNotFoundException("Product with ID " + productId + " not found");
        }
    }
}
