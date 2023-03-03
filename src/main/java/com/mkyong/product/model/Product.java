package com.mkyong.product.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Product {
    private int id;
    private String productName;
    private String productType;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public Product() {

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}

