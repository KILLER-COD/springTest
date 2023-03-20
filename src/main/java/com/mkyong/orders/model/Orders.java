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
    private List<String> goodsCount;
    private List<String> goodsId;

    public Orders(int id, int shopId, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.shopId = shopId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Orders(int shopId, Date createDate, Date modifyDate, Date deleteDate) {
        this.shopId = shopId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Orders(int shopId, Date createDate, Date modifyDate) {
        this.shopId = shopId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", create_date=" + createDate +
                ", modify_date=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
