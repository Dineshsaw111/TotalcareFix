package com.totalcarefix.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRequestUser {
    private String first_name;
    private String last_name;
    private String email;
    private String house_number;
    private String street ;
    private String society;
    private String locality;
    private String pincode;
    private String city;
    private String contact_number ;
    private String skill_name;
    private String usertype;
}
