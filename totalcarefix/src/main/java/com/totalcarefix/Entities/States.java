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
@Table(name = "states")
public class States {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int state_id;
    private String name;
}
