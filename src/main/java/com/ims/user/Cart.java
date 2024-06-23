package com.ims.user;

import com.ims.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class Cart {
    Map<Product, Integer> productVsCountMap;
    public Cart() {
        this.productVsCountMap = new HashMap<>();
    }

    //add item to cart
    public void addToCart(Product product, int count){
        if(productVsCountMap.containsKey(product)){
            int existingCount = productVsCountMap.get(product);
            productVsCountMap.put(product, existingCount + count);
        } else
            productVsCountMap.put(product, count);
    }

    //remove item to cart
    public void removeItemFromCart(Product product, int count) {
        if (productVsCountMap.containsKey(product)){
            int noOfItemsInCart = productVsCountMap.get(product);
            if (count - noOfItemsInCart == 0) {
                productVsCountMap.remove(product);
            } else {
                productVsCountMap.put(product, noOfItemsInCart - count);
            }
        }
    }

    //empty my cart
    public void emptyCart() {
        productVsCountMap = new HashMap<>();
    }

    //View Cart
    public  Map<Product, Integer> getCartItems(){
        return productVsCountMap;
    }
}
