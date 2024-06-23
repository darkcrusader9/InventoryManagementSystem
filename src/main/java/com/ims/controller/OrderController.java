package com.ims.controller;

import com.ims.order.Order;
import com.ims.user.User;
import com.ims.warehouse.Warehouse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class OrderController {
    Map<String, Order> orders;

    public OrderController() {
        this.orders = new HashMap<>();
    }

    public Order getOrderById(String orderId){
        return orders.get(orderId);
    }
    public List<Order> getAllOrdersForUser(String userId){
        List<Order> orderList = new ArrayList<>();
        for(Order order : orders.values()){
            if(order.getUser().getUserId().equals(userId))
                orderList.add(order);
        }
        return orderList;
    }

    public void placeOrder(User user, Warehouse warehouse){
        Order order = new Order(user, warehouse);
        order.checkout();
        orders.put(order.getOrderId(), order);
    }

}
