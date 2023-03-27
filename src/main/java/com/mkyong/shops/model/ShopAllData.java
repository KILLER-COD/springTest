package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopAllData {
    private int id;
    private String shopName;
    private String shopAddress;
    private String shopCity;
    private String shopOwner;
    private int hvhh;
    private String shopInfoAddress;
    private String shopInfoCity;
    private List<ShopContactingInfo> contactingInfoList;
    private Date createDate;
    private Date modifyDate;

    @Override
    public String toString() {
        return "ShopAllData{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopCity='" + shopCity + '\'' +
                ", shopOwner='" + shopOwner + '\'' +
                ", hvhh=" + hvhh +
                ", shopInfoAddress='" + shopInfoAddress + '\'' +
                ", shopInfoCity='" + shopInfoCity + '\'' +
                ", createDate=" + createDate + '\'' +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
