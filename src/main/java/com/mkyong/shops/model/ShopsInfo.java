package com.mkyong.shops.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
public class ShopsInfo {
    private int id;
    private String shopOwner;
    private int hvhh;
    private int addressId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public ShopsInfo() {

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
