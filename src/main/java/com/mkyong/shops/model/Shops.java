package com.mkyong.shops.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
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
