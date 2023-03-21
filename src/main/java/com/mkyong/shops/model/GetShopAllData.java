package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetShopAllData {
    private int id;
    private String shopName;
    private String shopAddress;
    private String shopCity;
    private String shopOwner;
    private int hvhh;
    private String shopInfoAddress;
    private String shopInfoCity;
    private Date createDate;
    private Date modifyDate;

    @Override
    public String toString() {
        return "GetShopAllData{" +
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
