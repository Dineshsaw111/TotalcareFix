package com.totalcarefix.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "addresses")
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int address_id;
    private int user_id;
    private String house_number;
    private String street ;
    private String society;
    private String locality;
    private int city_id;
    private String pincode;
    private  String address_type;
}
