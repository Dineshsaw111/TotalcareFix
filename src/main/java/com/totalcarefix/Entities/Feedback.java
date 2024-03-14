package com.totalcarefix.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int feedback_id;


    private int user_id;


    private int tech_id;


    private String message;


    private int rating;

    @Column(name = "creation_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp creation_time;
}
