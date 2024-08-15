package com.msp.shofydrop.products.repository.Implementation;

import com.msp.shofydrop.database.DefaultProcedureRepo;
import com.msp.shofydrop.products.Entity.Order;
import com.msp.shofydrop.products.Entity.OrderItems;
import com.msp.shofydrop.products.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderRepoImpl implements OrderRepo {

    @Autowired
    private DefaultProcedureRepo defaultProcedureRepo;

    @Override
    public Long save(Order order) {
        Object[] id = defaultProcedureRepo.executeWithType("orders.cfn_add_edit_orders", new Object[][]{
                {Long.class, order.getId(), "p_id"},
                {Long.class, order.getUserId(), "p_user_id"},
                {Long.class, order.getVendorId(), "p_vendor_id"},
                {ZonedDateTime.class, order.getOrderDate(), "p_order_date"},
                {String.class, order.getAddress(), "p_address"},
                {BigDecimal.class, order.getTotalPrice(), "p_total_price"},
                {Integer.class, order.getOrderStatus(), "p_status"},
                {String.class, order.getPromocode(), "p_promocode"},
                {Integer.class, order.getRewardsPoint(), "p_reward_point"}
        });
        return Long.valueOf(id[0].toString());
    }

    @Override
    public Optional<Object> findByUserId(Long userId) {
        List<Order> orderList = defaultProcedureRepo.getWithType("orders.cfn_get_orders_of_user",new Object[][]{
                {Long.class, userId,"p_user_id"},
        },Order.class);
        if (orderList.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(orderList.get(0));
    }

    @Override
    public Optional<Object> findByVendorId(Long vendorId) {
        List<Order> itemsList = defaultProcedureRepo.getWithType("orders.cfn_get_orders_of_vendor",new Object[][]{
                {Long.class,vendorId,"p_vendor_id"},
        },Order.class);
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
