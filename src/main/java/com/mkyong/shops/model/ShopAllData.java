package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;
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
    private String[] personName;
    private String[] personEmail;
    private List<String[]> personPhone;
    private List<ShopPersonContact> personPhoneTestList;
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
                ", personName=" + Arrays.toString(personName) +
                ", personEmail=" + Arrays.toString(personEmail) +
                ", personPhone=" + personPhone +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
    //    public List<ShopContactingInfo> setValuesShopContactInfoData() {
//        ShopContactingInfo shopContactingInfo = ShopContactingInfo.builder()
//                .id(0)
//                .shopInfoId(0)
//                .email("")
//                .name("")
//                .build();
//        List<ShopContactingInfo> shopContactingInfoList = new ArrayList<>();
//        shopContactingInfoList.add(shopContactingInfo);
//        return shopContactingInfoList;
//
//    }
}
