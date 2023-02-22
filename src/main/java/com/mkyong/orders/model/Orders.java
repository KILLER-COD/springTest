package com.mkyong.orders.model;

import java.util.Date;

public class Orders {

    private int id;
    private int shop_id;
    private Date create_date;
    private Date modify_date;
    private Date delete_date;

    public Orders(int id, int shop_id, Date create_date, Date modify_date, Date delete_date) {
        this.id = id;
        this.shop_id = shop_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public Orders( int shop_id, Date create_date, Date modify_date, Date delete_date) {
        this.shop_id = shop_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public Orders( int shop_id, Date create_date, Date modify_date) {
        this.shop_id = shop_id;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public Orders(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", shop_id=" + shop_id +
                ", create_date=" + create_date +
                ", modify_date=" + modify_date +
                ", delete_date=" + delete_date +
                '}';
    }
}
