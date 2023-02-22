package com.mkyong.shops.model;

import java.util.Date;

public class ShopsInfo {
    private int id;
    private String shop_owner;
    private String hvhh;
    private int address_id;
    private Date create_date;
    private Date modify_date;
    private Date delete_date;

    public ShopsInfo(){

    }

    public ShopsInfo(String shop_owner, String hvhh, int address_id, Date create_date, Date modify_date) {
        this.shop_owner = shop_owner;
        this.hvhh = hvhh;
        this.address_id = address_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public ShopsInfo(String shop_owner, String hvhh, int address_id, Date create_date, Date modify_date, Date delete_date) {
        this.shop_owner = shop_owner;
        this.hvhh = hvhh;
        this.address_id = address_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public ShopsInfo(int id, String shop_owner, String hvhh, int address_id, Date create_date, Date modify_date, Date delete_date) {
        this.id = id;
        this.shop_owner = shop_owner;
        this.hvhh = hvhh;
        this.address_id = address_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_owner() {
        return shop_owner;
    }

    public void setShop_owner(String shop_owner) {
        this.shop_owner = shop_owner;
    }

    public String getHvhh() {
        return hvhh;
    }

    public void setHvhh(String hvhh) {
        this.hvhh = hvhh;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getModify_date() {
        return modify_date;
    }

    public void setModify_date(Date modify_date) {
        this.modify_date = modify_date;
    }

    public Date getDelete_date() {
        return delete_date;
    }

    public void setDelete_date(Date delete_date) {
        this.delete_date = delete_date;
    }
}
