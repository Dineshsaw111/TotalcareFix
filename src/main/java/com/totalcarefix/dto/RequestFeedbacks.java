package com.totalcarefix.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RequestFeedbacks {
    private int user_id;
    private int tech_id;
    private String message;
    private int rating;
}
