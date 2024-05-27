package com.totalcarefix.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contacts")
public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int contact_id ;

    @Column(name = "user_id")
    private  int   userId ;
    private String contact_number ;
}
