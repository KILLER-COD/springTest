package com.mkyong.address.model;

import java.util.Date;

public class Address {
    private int id;
    private String address;
    private String city;
    private Date create_date;
    private Date modify_date;
    private Date delete_date;

    public Address(int id, String address, String city, Date create_date, Date modify_date, Date delete_date) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }
    public Address( String address, String city, Date create_date, Date modify_date, Date delete_date) {
        this.address = address;
        this.city = city;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.delete_date = delete_date;
    }
    public Address(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getModify_date() {
        return modify_date;
    }

    public void setModify_date(Date modify_date) {
        this.modify_date = modify_date;
    }

    public Date getDelete_date() {
        return delete_date;
    }

    public void setDelete_date(Date delete_date) {
        this.delete_date = delete_date;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", create_date=" + create_date +
                ", modify_date=" + modify_date +
                ", delete_date=" + delete_date +
                '}';
    }

}
