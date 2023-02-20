package com.mkyong.goods.model;

import java.util.Date;

public class Goods {

    private int id;
    private String goods_name;
    private String goods_type;
    private double goods_price;
    private int product_id;
    private Date create_date;
    private Date modify_date;
    private Date delete_date;


    public Goods(int id, String goods_name, String goods_type, double goods_price, int product_id, Date create_date, Date modify_date, Date delete_date) {
        this.id = id;
        this.goods_name = goods_name;
        this.goods_type = goods_type;
        this.goods_price = goods_price;
        this.product_id = product_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public Goods(String goods_name, String goods_type, double goods_price, int product_id, Date create_date, Date modify_date) {
        this.goods_name = goods_name;
        this.goods_type = goods_type;
        this.goods_price = goods_price;
        this.product_id = product_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public Goods(int id, String goodsName, String goodsType, double goodsPrice, java.sql.Date createDate, java.sql.Date modifyDate, java.sql.Date deleteDate){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
