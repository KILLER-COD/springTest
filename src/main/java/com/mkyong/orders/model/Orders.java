package com.mkyong.orders.model;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private int id;
    private int shopId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;
    private List<Integer> goodsId;
    private List<Double> goodsCount;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                ", goodsId=" + goodsId +
                ", goodsCount=" + goodsCount +
                '}';
    }
}
