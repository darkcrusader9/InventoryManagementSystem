package com.ims.warehouse;

import com.ims.product.Product;
import com.ims.user.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class Warehouse {
    Inventory inventory;
    Address address;

    public Warehouse(Inventory inventory, Address address) {
        this.inventory = inventory;
        this.address = address;
    }

    //update inventory
    public void removeItemsFromInventory(Map<Product, Integer> productAndCountMap){
        inventory.removeItemsFromInventory(productAndCountMap);
    }

    //it will update the items in the inventory based upon product category.
    public void addItemToInventory(Map<Product, Integer> productCategoryAndCountMap){
        for(Map.Entry<Product, Integer> mp : productCategoryAndCountMap.entrySet())
            inventory.addProduct(mp.getKey(), mp.getValue());

    }

}
