package com.mkyong.shops.model;

import java.util.Date;

public class Shops {
    private int id;
    private String shop_name;
    private int shop_address_id;
    private int shop_info_id;
    private Date create_date;
    private Date modify_date;
    private Date delete_date;


    public Shops(){

    }

    public Shops(String shop_name, int shop_address_id, int shop_info_id, Date create_date, Date modify_date) {
        this.shop_name = shop_name;
        this.shop_address_id = shop_address_id;
        this.shop_info_id = shop_info_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public Shops(String shop_name, int shop_address_id, int shop_info_id, Date create_date, Date modify_date, Date delete_date) {
        this.shop_name = shop_name;
        this.shop_address_id = shop_address_id;
        this.shop_info_id = shop_info_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public Shops(int id, String shop_name, int shop_address_id, int shop_info_id, Date create_date, Date modify_date, Date delete_date) {
        this.id = id;
        this.shop_name = shop_name;
        this.shop_address_id = shop_address_id;
        this.shop_info_id = shop_info_id;
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

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getShop_address_id() {
        return shop_address_id;
    }

    public void setShop_address_id(int shop_address_id) {
        this.shop_address_id = shop_address_id;
    }

    public int getShop_info_id() {
        return shop_info_id;
    }

    public void setShop_info_id(int shop_info_id) {
        this.shop_info_id = shop_info_id;
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
