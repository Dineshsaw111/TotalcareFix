package com.totalcarefix.DTO;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackRequest {
    private int bookingId;
    private String userEmail;
    private  String techEmail;
    private  String message;
    private  int rating;
}
