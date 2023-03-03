package com.mkyong.address.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCountByCity {
    private int count;
    private String city;

    public AddressCountByCity(int count, String city) {
        this.count = count;
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
