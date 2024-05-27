package com.totalcarefix.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackRequest {
    private int bookingId;
    private  String message;
    private  int rating;
}
