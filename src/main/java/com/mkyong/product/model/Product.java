package com.mkyong.product.model;

import java.sql.Date;

public class Product {

    private int id;
    private String productName;
    private String productType;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public Product(){
        
    }
    public Product(String productName, String productType, Date createDate, Date modifyDate) {
        this.productName = productName;
        this.productType = productType;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Product(String productName, String productType, Date createDate, Date modifyDate, Date deleteDate) {
        this.productName = productName;
        this.productType = productType;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Product(int id, String productName, String productType, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

