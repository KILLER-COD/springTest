package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopContactingInfo {

    private int id;
    private int shopInfoId;
    private String name;
    private String phone1;
    private String phone2;
    private String email;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    @Override
    public String toString() {
        return "ShopContactingInfo{" +
                "id=" + id +
                ", shopInfoId=" + shopInfoId +
                ", name='" + name + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
