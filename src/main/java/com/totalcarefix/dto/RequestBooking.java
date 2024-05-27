package com.totalcarefix.dto;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestBooking {
    private int bookerId;
    private Integer techId;
    private int statusId;
    private int addressId;
    private String message;
    private Timestamp serviceDate;
    private Time expectedTime;
}
