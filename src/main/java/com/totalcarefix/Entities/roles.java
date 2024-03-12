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
public class roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;
    private String name;
}
