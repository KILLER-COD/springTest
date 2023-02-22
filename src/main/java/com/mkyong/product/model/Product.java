package com.mkyong.product.model;

import java.util.Date;

public class Product {

    private int id;
    private String product_name;
    private String product_type;
    private Date create_date;
    private Date modify_date;
    private Date delete_date;

    public Product(){
        
    }
    public Product(String product_name, String product_type, Date create_date, Date modify_date) {
        this.product_name = product_name;
        this.product_type = product_type;
        this.create_date = create_date;
        this.modify_date = modify_date;
    }

    public Product(String product_name, String product_type, Date create_date, Date modify_date, Date delete_date) {
        this.product_name = product_name;
        this.product_type = product_type;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }

    public Product(int id, String product_name, String product_type, Date create_date, Date modify_date, Date delete_date) {
        this.id = id;
        this.product_name = product_name;
        this.product_type = product_type;
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

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
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

