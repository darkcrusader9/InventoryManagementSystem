package com.ims.order;

import com.ims.payment.Payment;
import com.ims.payment.UPIPayment;
import com.ims.product.Product;
import com.ims.user.User;
import com.ims.warehouse.Inventory;
import com.ims.warehouse.Warehouse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter
@ToString
public class Order {
    String orderId;
    User user;
    Map<Product, Integer> productCountMap;
    OrderStatus orderStatus;
    double orderAmt;
    Invoice invoice;
    boolean isPaid;
    Warehouse warehouse;
    ReentrantLock lock;
    Payment payment;
    public Order(User user, Warehouse warehouse){
        this.orderId = UUID.randomUUID().toString();
        this.user = user;
        this.productCountMap = this.user.getCart().getCartItems();
        generateInvoice();
        this.isPaid = false;
        this.orderStatus = OrderStatus.NOT_DELIVERED;
        this.warehouse = warehouse;
        lock = new ReentrantLock();
        this.payment = new UPIPayment();
    }

    public void generateInvoice(){
        double amt = 0;
        for(Map.Entry<Product, Integer> mp : this.productCountMap.entrySet()){
            amt += mp.getKey().getPrice() * mp.getValue();
        }
        this.orderAmt = amt;
        this.invoice = new Invoice(this.orderAmt);
    }

    public void checkout(){
        Inventory inventory = warehouse.getInventory();
        this.isPaid = payment.makePayment();
        if(!inventory.checkItemsInInventory(this.productCountMap)){
            System.out.println("Your order cannot be filled, some items are out of stock!!");
            this.isPaid = false;
            user.getCart().emptyCart();
        } else {
            lock.lock();
            try{
                warehouse.removeItemsFromInventory(this.productCountMap);
            } finally {
                lock.unlock();
            }

            if(isPaid) {
                user.getCart().emptyCart();
                this.orderStatus = OrderStatus.DELIVERED;
            }
            else{
                warehouse.addItemToInventory(this.productCountMap);
            }
        }
    }

    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCELLED;
        this.warehouse.addItemToInventory(this.productCountMap);
        this.productCountMap = new HashMap<>();
    }
}
