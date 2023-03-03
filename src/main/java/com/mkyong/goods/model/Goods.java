package com.mkyong.goods.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Goods {
    private int id;
    private String goodsName;
    private String goodsType;
    private double goodsPrice;
    private int productId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;


    public Goods(int id, String goodsName, String goodsType, double goodsPrice, int productId, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsPrice = goodsPrice;
        this.productId = productId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Goods(String goodsName, String goodsType, double goodsPrice, int productId, Date createDate, Date modifyDate) {
        this.goodsName = goodsName;
        this.goodsType = goodsType;
        this.goodsPrice = goodsPrice;
        this.productId = productId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Goods() {

    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", productId=" + productId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
