package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopPersonContact {
    private int id;
    private String phone;
    private int shopPersonDataId;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    @Override
    public String toString() {
        return "ShopPersonContact{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", shopPersonDataId=" + shopPersonDataId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
