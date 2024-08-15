package com.msp.shofydrop.products.repository.Implementation;

import com.msp.shofydrop.authentication.entity.Users;
import com.msp.shofydrop.database.DefaultProcedureRepo;
import com.msp.shofydrop.products.Entity.Products;
import com.msp.shofydrop.products.Entity.SavedProduct;
import com.msp.shofydrop.products.repository.SavedProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SavedProductRepoImpl implements SavedProductRepo {

    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public Long save(SavedProduct savedProduct) {
        Object id[] = defaultProcedureRepo.executeWithType("products.cfn_add_edit_saved_product",new Object[][]{
                {Long.class,savedProduct.getId(),"p_id"},
                {Long.class,savedProduct.getProductId(),"p_product_id"},
                {Long.class,savedProduct.getUserId(),"p_user_id"}
        });
        return Long.valueOf(id[0].toString());
    }

    @Override
    public Optional<SavedProduct> findById(Long id) {
        List<SavedProduct> savedProductList = defaultProcedureRepo.getWithType("products.cfn_get_saved_product_by_user",new Object[][]{
                {Long.class, id, "p_id"},
        },SavedProduct.class);
        if (savedProductList.isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(savedProductList.get(0));
        }
    }

    @Override
    public Optional<SavedProduct> findByUserId(Long id) {
        List<SavedProduct> results = defaultProcedureRepo.getWithType("products.cfn_get_saved_product_by_user",new Object[][]{
                        {Long.class, id, "p_user_id"},
                },SavedProduct.class);

        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }


    @Override
    public List<SavedProduct> findByProduct(Products product) {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByUser(Users user) {

    }

    @Override
    public void deleteByProduct(Products product) {

    }

    @Override
    public List<SavedProduct> findAll() {
        return List.of();
    }
}
