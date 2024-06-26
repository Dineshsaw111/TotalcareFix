package com.totalcarefix.dto;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBookingRequest {
    private  String email;
    private  String skill;
    private Timestamp serviceDate;
    private Time time;
    private String message;
}
