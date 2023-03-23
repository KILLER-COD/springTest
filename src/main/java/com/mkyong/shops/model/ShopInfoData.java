package com.mkyong.shops.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopInfoData {
    private int id;
    private String shopOwner;
    private int hvhh;
    private String shopInfoAddress;
    private String shopInfoCity;

    @Override
    public String toString() {
        return "ShopInfoData{" +
                "id=" + id +
                ", shopOwner='" + shopOwner + '\'' +
                ", hvhh=" + hvhh +
                ", shopInfoAddress='" + shopInfoAddress + '\'' +
                ", shopInfoCity='" + shopInfoCity + '\'' +
                '}';
    }
}
