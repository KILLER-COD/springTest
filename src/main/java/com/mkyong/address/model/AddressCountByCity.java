package com.mkyong.address.model;

public class AddressCountByCity {
    private int count;
    private String city;

    public AddressCountByCity(int count, String city) {
        this.count = count;
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressCountByCity{" +
                "count=" + count +
                ", city='" + city + '\'' +
                '}';
    }
}
