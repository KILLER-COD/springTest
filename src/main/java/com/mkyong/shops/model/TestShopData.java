package com.mkyong.shops.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestShopData {
    private String[] personName;
    private String[] personEmail;
    private List<String[]> personPhone;

    @Override
    public String toString() {
        return "TestShopData{" +
                "personName=" + Arrays.toString(personName) +
                ", personEmail=" + Arrays.toString(personEmail) +
                ", personPhone=" + personPhone +
                '}';
    }
}
