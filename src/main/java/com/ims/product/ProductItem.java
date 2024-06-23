package com.ims.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class ProductItem {
    String productItemId;
    public ProductItem() {
        this.productItemId = UUID.randomUUID().toString();
    }
}
