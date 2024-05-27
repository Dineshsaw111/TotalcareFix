package com.totalcarefix.dto;


import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookingRequest {
    private  String email;
    private Timestamp serviceDate;
    private Time time;
    private String message;
}
