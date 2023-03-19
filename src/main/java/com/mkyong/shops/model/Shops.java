package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shops {
    private int id;
    private String shopName;
    private int shopAddressId;
    private int shopInfoId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;


    @Override
    public String toString() {
        return "Shops{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", shopAddressId=" + shopAddressId +
                ", shopInfoId=" + shopInfoId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
