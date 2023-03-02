package com.mkyong.shops.model;

import java.sql.Date;

public class Shops {
    private int id;
    private String shopName;
    private int shopAddressId;
    private int shopInfoId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;


    public Shops() {

    }

    public Shops(String shopName, int shopAddressId, int shopInfoId, Date createDate, Date modifyDate) {
        this.shopName = shopName;
        this.shopAddressId = shopAddressId;
        this.shopInfoId = shopInfoId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Shops(String shopName, int shopAddressId, int shopInfoId, Date createDate, Date modifyDate, Date deleteDate) {
        this.shopName = shopName;
        this.shopAddressId = shopAddressId;
        this.shopInfoId = shopInfoId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Shops(int id, String shopName, int shopAddressId, int shopInfoId, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.shopName = shopName;
        this.shopAddressId = shopAddressId;
        this.shopInfoId = shopInfoId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getShopAddressId() {
        return shopAddressId;
    }

    public void setShopAddressId(int shopAddressId) {
        this.shopAddressId = shopAddressId;
    }

    public int getShopInfoId() {
        return shopInfoId;
    }

    public void setShopInfoId(int shopInfoId) {
        this.shopInfoId = shopInfoId;
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
