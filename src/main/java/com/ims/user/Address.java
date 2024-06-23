package com.ims.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {
    String address;

    public Address(String address) {
        this.address = address;
    }
}
