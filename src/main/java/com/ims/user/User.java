package com.ims.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    String userId;
    String password;
    Cart cart;
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.cart = null;
    }
}
