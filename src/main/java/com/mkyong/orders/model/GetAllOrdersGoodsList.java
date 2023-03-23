package com.mkyong.orders.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllOrdersGoodsList {
    private int id;
    private int goodsId;
    private String goodsName;
    private String goodsCount;
    private String goodsType;
    private Double goodsPrice;
    private Date createDate;
    private Date modifyDate;

    @Override
    public String toString() {
        return "GetAllOrdersGoodsList{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount='" + goodsCount + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}' + "\n";
    }
}
