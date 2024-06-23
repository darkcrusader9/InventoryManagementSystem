package com.ims;

import com.ims.controller.*;
import com.ims.order.*;
import com.ims.product.Product;
import com.ims.warehouse.*;
import com.ims.user.*;
import com.ims.strategy.*;

import java.util.ArrayList;
import java.util.List;


public class InventoryManagementService {
    public static void main(String[] args) {
        UserController userController = new UserController();
        OrderController orderController = new OrderController();

        Inventory inventory = new Inventory();
        Address address = new Address("123 Main St");
        Warehouse warehouse = new Warehouse(inventory, address);
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse);

        // Create a user
        User user = new User("user1", "password");
        user.setCart(new Cart());
        userController.addUser(user);

        // Create a product
        Product product = new Product("Laptop", 100.0, "High-end gaming laptop");
        Product product1 = new Product("Mouse", 20.0, "Mouse hardware");
        Product product2 = new Product("Keyboard", 30.0, "Keyboard for typing");
        // Add products to the inventory
        inventory.addProduct(product, 10);
        inventory.addProduct(product1, 5);
        inventory.addProduct(product2, 3);

        WarehouseController warehouseController = new WarehouseController(warehouses, new NearestWarehouseSelectionStrategy());
        Warehouse assignedWarehouse = warehouseController.selectWarehouse();



        // Add product to the user's cart
        user.getCart().addToCart(product, 2);
        user.getCart().addToCart(product1, 2);

        orderController.placeOrder(user, assignedWarehouse);

        //The following order should not be placed as there as not enough items in inventory

        user.getCart().addToCart(product, 5);
        user.getCart().addToCart(product1, 4);

        orderController.placeOrder(user, assignedWarehouse);

        List<Order> orders = orderController.getAllOrdersForUser(user.getUserId());
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Amount: " + order.getOrderAmt());
            System.out.println("Order Status: " + order.getOrderStatus());
        }


    }
}
