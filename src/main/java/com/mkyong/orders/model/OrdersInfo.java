package com.mkyong.orders.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersInfo {
    private int id;
    private int ordersId;
    private int shopsId;
    private String shopName;
    private String goodsName;
    private double goodsCount;
    private double goodsPrice;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    @Override
    public String toString() {
        return "OrdersInfo{" +
                "id=" + id +
                ", ordersId=" + ordersId +
                ", shopsId=" + shopsId +
                ", shopName='" + shopName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount=" + goodsCount +
                ", goodsPrice=" + goodsPrice +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
