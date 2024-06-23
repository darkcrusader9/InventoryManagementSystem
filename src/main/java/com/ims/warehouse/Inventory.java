package com.ims.warehouse;

import com.ims.product.Product;
import com.ims.product.ProductItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
public class Inventory {
    Map<Product, Integer> productVsCountMap;

    public Inventory() {
        this.productVsCountMap = new HashMap<>();
    }

    public void addProduct(Product product, int count){
        for(int i = 0; i < count; i++){
            ProductItem item = new ProductItem();
            product.addItem(item);
        }
        if(!productVsCountMap.containsKey(product)){
            productVsCountMap.put(product, count);
        } else {
            productVsCountMap.put(product, productVsCountMap.get(product) + count);
        }
    }

    public void removeProduct(Product product, int count){
        List<ProductItem> itemList = product.getProductItemList();
        if (count > 0) {
            itemList.subList(0, count).clear();
        }
        product.setProductItemList(itemList);
        productVsCountMap.put(product, product.getProductItemList().size());
    }

    public void removeItemsFromInventory(Map<Product, Integer> productCountMap){
        for(Map.Entry<Product, Integer> mp : productCountMap.entrySet()){
            removeProduct(mp.getKey(), mp.getValue());
        }
    }

    public boolean checkItemsInInventory(Map<Product, Integer> productCountMap){
        for(Map.Entry<Product, Integer> entry : productCountMap.entrySet()){
            Product product = entry.getKey();
            int requiredCount = entry.getValue();
            if(!productVsCountMap.containsKey(product) || productVsCountMap.get(product) < requiredCount) {
                return false;
            }
        }
        return true;
    }
}
