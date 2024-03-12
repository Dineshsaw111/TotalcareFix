package com.totalcarefix.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_status")
public class UsersStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int users_status_id;
    private String name;
}
