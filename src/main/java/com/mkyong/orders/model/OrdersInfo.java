package com.mkyong.orders.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrdersInfo {
    private int ordersId;
    private String shopName;
    private String goodsName;
    private double goodsCount;
    private double goodsPrice;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public OrdersInfo() {

    }

    public OrdersInfo(int ordersId, String shopName, String goodsName, double goodsCount, double goodsPrice, Date createDate, Date modifyDate, Date deleteDate) {
        this.ordersId = ordersId;
        this.shopName = shopName;
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.goodsPrice = goodsPrice;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

}
