package com.mkyong.address.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Address {
    private int id;
    private String address;
    private String city;
    private Date createDate;
    private Date modifyDate;
    private Date deleteDate;

    public Address(int id, String address, String city, Date createDate, Date modifyDate, Date deleteDate) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Address(String address, String city, Date createDate, Date modifyDate, Date deleteDate) {
        this.address = address;
        this.city = city;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public Address() {

    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }

}
