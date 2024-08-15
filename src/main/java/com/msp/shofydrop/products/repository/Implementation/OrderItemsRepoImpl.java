package com.msp.shofydrop.products.repository.Implementation;

import com.msp.shofydrop.database.DefaultProcedureRepo;
import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import com.msp.shofydrop.products.repository.OrderItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemsRepoImpl implements OrderItemsRepo {

    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public Long save(OrderItems orderItems) {
        Object id[] = defaultProcedureRepo.executeWithType("orders.cfn_add_edit_order_items",new Object[][]{
                {Long.class,orderItems.getOrderId(),"p_order_id"},
                {Long.class,orderItems.getProductId(),"p_product_id"},
                {BigDecimal.class,orderItems.getPrice(),"p_price"},
                {Integer.class,orderItems.getQuantity(),"p_quantity"},
                {BigDecimal.class,orderItems.getTotal(),"p_total"}
        });
        return Long.valueOf(id[0].toString());
    }

    @Override
    public Optional<Object> findOrderItemsByUserId(Long userId) {
        List<OrderItems> itemsList = defaultProcedureRepo.getWithType("orders.cfn_get_orders_of_user",new Object[][]{
                {Long.class,userId,"p_user_id"},
        },OrderItems.class);
        if (itemsList.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(itemsList.get(0));
    }

    @Override
    public Optional<Object> findOrderItemsByVendorId(Long vendorId) {
        List<OrderItems> itemsList = defaultProcedureRepo.getWithType("orders.cfn_get_orders_of_vendor",new Object[][]{
                {Long.class, vendorId, "p_vendor_id"},
        },OrderItems.class);
        if (itemsList.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(itemsList.get(0));
    }

    @Override
    public List<Order> findByOrderStatus(String orderStatus) {
        return List.of();
    }

    @Override
    public List<Order> findByOrderStatusAndPaymentStatus(String orderStatus, String paymentStatus) {
        return List.of();
    }
}
