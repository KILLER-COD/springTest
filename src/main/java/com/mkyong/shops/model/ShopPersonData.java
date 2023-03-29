package com.mkyong.shops.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopPersonData {

    private int id;
    private int shopId;
    private String personName;
    private String personEmail;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    @Override
    public String toString() {
        return "ShopPersonData{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", name='" + personName + '\'' +
                ", email='" + personEmail + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
