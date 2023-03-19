package com.mkyong.shops.model;

import com.mkyong.address.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewShopInfo {
    private ShopsInfo shopsInfo;
    private Address address;

    @Override
    public String toString() {
        return "NewShopInfo{" +
                "shopsInfo=" + shopsInfo +
                ", address=" + address +
                '}';
    }
}
