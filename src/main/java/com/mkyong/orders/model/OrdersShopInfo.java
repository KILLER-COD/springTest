package com.mkyong.orders.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersShopInfo {
    private int id;
    private String shopName;
    private String address;
    private String city;
    private Date createDate;

    @Override
    public String toString() {
        return "OrdersShopInfo{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
