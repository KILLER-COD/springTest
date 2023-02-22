package com.mkyong.shops.model;

import java.util.Date;

public class ShopsInfo {
    private int id;
    private String shopOwner;
    private int hvhh;
    private int addressId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public ShopsInfo(){

    }

    public ShopsInfo(String shopOwner, int hvhh, int addressId, Date createDate, Date modifyDate) {
        this.shopOwner = shopOwner;
        this.hvhh = hvhh;
        this.addressId = addressId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public ShopsInfo(String shopOwner, int hvhh, int addressId, Date createDate, Date modifyDate, Date deleteDate) {
        this.shopOwner = shopOwner;
        this.hvhh = hvhh;
        this.addressId = addressId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public ShopsInfo(int id, String shopOwner, int hvhh, int addressId, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.shopOwner = shopOwner;
        this.hvhh = hvhh;
        this.addressId = addressId;
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

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public int getHvhh() {
        return hvhh;
    }

    public void setHvhh(int hvhh) {
        this.hvhh = hvhh;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
