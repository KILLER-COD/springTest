package com.mkyong.shops.model;

import com.mkyong.address.model.Address;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllShopsData {
    private Shops shops;
    private Address addressShop;
    private NewShopInfo newShopInfo;

    @Override
    public String toString() {
        return "AllShopsData{" +
                "shops=" + shops +
                ", addressShop=" + addressShop +
                ", newShopInfo=" + newShopInfo +
                '}';
    }

    private List<ShopsInfo> shopsInfoList = getShopsInfoList();


}
