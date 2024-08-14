package com.msp.shofydrop.products.repository.Implementation;

import com.msp.shofydrop.database.DefaultProcedureRepo;
import com.msp.shofydrop.products.Entity.Image;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductsRepoImpl implements ProductsRepo {

    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public List<Products> getProducts(Long id) {
        return defaultProcedureRepo.getWithType("products.cfn_get_products",new Object[][]{
                {Long.class, id,"p_id"},
        }, Products.class);
    }

    @Override
    public List<Image> getImages(Long id) {
        return defaultProcedureRepo.getWithType("images.cfn_get_products_image",new Object[][]{
                {Long.class, id,"p_product_id"},
        },Image.class);
    }

    @Override
    public Long saveProduct(Products products) {
        Object id[] = defaultProcedureRepo.executeWithType("products.cfn_add_edit_products",new Object[][] {
                {BigDecimal.class,products.getDiscountedPrice(),"p_discounted_price"},
                {BigDecimal.class,products.getPrice(),"p_price"},
                {Long.class,products.getStock(),"p_stock"},
                {Long.class,products.getCategoryId(),"p_category_id"},
                {Long.class,products.getId(),"p_id"},
                {Long.class,products.getSubcategoryId(),"p_subcategory_id"},
                {String.class,products.getDescription(),"p_description"},
                {String.class,products.getProductName(),"p_name"},
                {Long.class, products.getVendorId(), "p_vendor_id"},
        });
        return Long.valueOf(id[0].toString());
    }

    @Override
    public String saveImage(Image image) {
        Object[] id = defaultProcedureRepo.executeWithType("images.cfn_add_edit_products_image", new Object[][]{
                {Long.class, image.getId(), "p_id"},
                {Long.class, image.getProductId(), "p_product_id"},
                {String.class, image.getImages(), "p_images"},
        });
        return id[0].toString();
    }

    @Override
    public List<Products> findBynameContaining(String name) {
        return List.of();
    }

    @Override
    public void deleteProductProductById(Long productId) {

    }

    @Override
    public List<Products> findByNameContaining(String name) {
        return defaultProcedureRepo.getWithType(
                "products.cfn_find_by_name",
                new Object[][]{
                        {String.class, "%" + name + "%", "name"}
                },
                Products.class
        );
    }

    @Override
    public void deleteProduct(Integer productId) {
        defaultProcedureRepo.executeWithType("", new Object[][]{
                        {Integer.class, productId, "p_product_id"}
                }
        );
    }
}
