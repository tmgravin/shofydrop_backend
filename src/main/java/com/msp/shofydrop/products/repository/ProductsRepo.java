package com.msp.shofydrop.products.repository;

import com.msp.shofydrop.products.Entity.Products;

import java.util.List;

public interface ProductsRepo {

    List<Products> getProducts(Long id);

    Long saveProduct(Products products);

    List<Products> findBynameContaining(String name);

    //List<Products> getAllProducts();

    void deleteProductProductById(Long productId);

    //List<Products> indByNameContaining(String name);

    List<Products> findByNameContaining(String name);

    //    @Override
    //    public List<Products> findByNameContaining(String name) {
    //        return defaultProcedureRepo.getWithType(
    //                "products.cfn_find_by_name",
    //                new Object[][]{
    //                        {String.class, "%" + name + "%", "name"}
    //                },
    //                Products.class
    //        );
    //    }
    //
    void deleteProduct(Integer productId);

//    List<Products> findByNameContaining(String name);
//
//    void deleteProduct(Integer productId);
}
