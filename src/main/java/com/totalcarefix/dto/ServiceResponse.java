package com.totalcarefix.dto;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse {
    private int bookingId;
    private String name;
    private String mobileNumber;
    private String message;
   // private String skill;
    private Timestamp serviceDate;
    private Time expectedTime;
    private AddressResponse addressResponse;
}
