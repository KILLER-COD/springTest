package com.mkyong.orders.model;

import java.sql.Date;

public class Orders {

    private int id;
    private int shopId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public Orders(int id, int shopId, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.shopId = shopId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Orders(int shopId, Date createDate, Date modifyDate, Date deleteDate) {
        this.shopId = shopId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Orders(int shopId, Date createDate, Date modifyDate) {
        this.shopId = shopId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Orders() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", create_date=" + createDate +
                ", modify_date=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
