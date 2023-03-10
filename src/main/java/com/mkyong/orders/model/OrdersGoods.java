package com.mkyong.orders.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrdersGoods {
    private int id;
    private int ordersId;
    private int goodsId;
    private double goodsCount;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public OrdersGoods(int id, int ordersId, int goodsId, double goodsCount, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.ordersId = ordersId;
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public OrdersGoods(int ordersId, int goodsId, double goodsCount, Date createDate, Date modifyDate, Date deleteDate) {
        this.ordersId = ordersId;
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public OrdersGoods(int ordersId, int goodsId, double goodsCount, Date createDate, Date modifyDate) {
        this.ordersId = ordersId;
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public OrdersGoods() {

    }

    @Override
    public String toString() {
        return "OrdersGoods{" +
                "id=" + id +
                ", ordersId=" + ordersId +
                ", goodsId=" + goodsId +
                ", goodsCount=" + goodsCount +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
