package com.totalcarefix.DTO;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBookingResponse {
    private int BookingId;
    private String message;
    private  String status;
    private  String skill;
    private Timestamp date;
    private Time time;
    private  int feedbackId=0;
}
