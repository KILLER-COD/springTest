package com.mkyong.shops.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewShopInfo {
    private int id;
    private String shopOwner;
    private int hvhh;
    private String shopInfoAddress;
    private String shopInfoCity;

    @Override
    public String toString() {
        return "NewShopInfo{" +
                "id=" + id +
                ", shopOwner='" + shopOwner + '\'' +
                ", hvhh=" + hvhh +
                ", shopInfoAddress='" + shopInfoAddress + '\'' +
                ", shopInfoCity='" + shopInfoCity + '\'' +
                '}';
    }
}
