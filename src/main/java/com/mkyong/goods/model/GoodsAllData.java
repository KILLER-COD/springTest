package com.mkyong.goods.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsAllData {
    private int id;
    private String goodsName;
    private String goodsType;
    private double goodsPrice;
    private int productId;
    private String productType;
    private String productName;
    private Date createDate;
    private Date modifyDate;

    @Override
    public String toString() {
        return "GoodsAllData{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", productId=" + productId +
                ", productType='" + productType + '\'' +
                ", productName='" + productName + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
