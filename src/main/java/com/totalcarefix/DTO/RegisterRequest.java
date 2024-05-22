package com.totalcarefix.DTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String houseNo;
    private String society;
    private String street;
    private  String locality;
    private String city;
    private String pincode;
    private String contact;
    private String skill;
}
