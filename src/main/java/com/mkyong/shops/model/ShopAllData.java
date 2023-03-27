package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
    //    private List<ShopContactingInfo> contactingInfoList = setValuesShopContactInfoData();
    private String[] name;
    private String[] email;
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
                ", name=" + Arrays.toString(name) +
                ", email=" + Arrays.toString(email) +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }

    public List<ShopContactingInfo> setValuesShopContactInfoData() {
        ShopContactingInfo shopContactingInfo = ShopContactingInfo.builder()
                .id(0)
                .shopInfoId(0)
                .email("")
                .name("")
                .build();
        List<ShopContactingInfo> shopContactingInfoList = new ArrayList<>();
        shopContactingInfoList.add(shopContactingInfo);
        return shopContactingInfoList;

    }
}
