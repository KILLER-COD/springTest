package com.mkyong.shops.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonInfoData {
    private int personDataId;
    private int personContactId;
    private String personName;
    private String personEmail;
    private String[] personPhone;

}
