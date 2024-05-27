package com.totalcarefix.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    private int bookerId;

    private Integer techId;

    private int statusId;

    private int addressId;

    private int skillId;

    private String message;

    private Timestamp serviceDate;

    private Time expectedTime;
    @Column(name = "logtime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private Timestamp logtime;
}
