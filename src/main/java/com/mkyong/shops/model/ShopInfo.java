package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopInfo {
    private int id;
    private String shopOwner;
    private int hvhh;
    private int addressId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    @Override
    public String toString() {
        return "ShopsInfo{" +
                "id=" + id +
                ", shopOwner='" + shopOwner + '\'' +
                ", hvhh=" + hvhh +
                ", addressId=" + addressId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
