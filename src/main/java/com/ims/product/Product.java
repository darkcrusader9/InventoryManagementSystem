package com.ims.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Product {
    String productId;
    String productName;
    double price;
    String description;
    List<ProductItem> productItemList;

    public Product(String productName, double price, String description) {
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.productItemList = new ArrayList<>();
    }

    public int getCount(){
        return productItemList.size();
    }

    public boolean addItem(ProductItem item){
        if(productItemList.contains(item))
            return false;
        productItemList.add(item);
        return true;
    }

    public boolean removeProductItem(ProductItem item){
        if(productItemList.contains(item)){
            productItemList.remove(item);
            return true;
        }
        return false;
    }

    public boolean updateProductItem(ProductItem item){
        ProductItem productItemToBeUpdated = null;
        for(ProductItem pi : productItemList){
            if(pi.getProductItemId() == item.getProductItemId()){
                productItemToBeUpdated = pi;
            }
        }
        if(productItemToBeUpdated == null)
            return false;
        // change any property of productItemToBeUpdated
        return true;
    }
}
