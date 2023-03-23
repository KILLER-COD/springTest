package com.mkyong.orders.model;

import com.mkyong.shops.model.GetShopAllData;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAlOrdersData {
    private int id;
    private GetShopAllData ordersShopAllData;
    private List<GetAllOrdersGoodsList> orderGoodsList;
    private Date createDate;
    private Date modifyDate;

    @Override
    public String toString() {
        return "GetAlOrdersData{" +
                "id=" + id +
                ", ordersShopAllData=" + ordersShopAllData +
                ", orderGoodsList=" + orderGoodsList +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}