package com.mkyong.orders.model;

import java.util.Date;

public class OrdersGoods {

    private int id;
    private int orders_id;
    private int goods_id;
    private double goods_count;
    private Date create_date;
    private Date modify_date;
    private Date delete_date;

    public OrdersGoods(int id, int orders_id, int goods_id, double goods_count, Date create_date, Date modify_date, Date delete_date) {
        this.id = id;
        this.orders_id = orders_id;
        this.goods_id = goods_id;
        this.goods_count = goods_count;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public OrdersGoods(int orders_id, int goods_id, double goods_count, Date create_date, Date modify_date, Date delete_date) {
        this.orders_id = orders_id;
        this.goods_id = goods_id;
        this.goods_count = goods_count;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public OrdersGoods(int orders_id, int goods_id, double goods_count, Date create_date, Date modify_date) {
        this.orders_id = orders_id;
        this.goods_id = goods_id;
        this.goods_count = goods_count;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public OrdersGoods(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(int orders_id) {
        this.orders_id = orders_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public double getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(double goods_count) {
        this.goods_count = goods_count;
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
