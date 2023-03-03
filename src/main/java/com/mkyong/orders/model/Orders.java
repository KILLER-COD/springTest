package com.mkyong.orders.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Orders {
    private int id;
    private int shopId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

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

    public Orders() {

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
