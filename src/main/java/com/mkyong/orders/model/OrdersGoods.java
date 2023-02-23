package com.mkyong.orders.model;

import java.sql.Date;

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

    public OrdersGoods(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public double getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(double goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }


}
