package com.totalcarefix.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private String houseNo;
    private String society;
    private String street;
    private  String locality;
    private String city;
    private String state;
}
