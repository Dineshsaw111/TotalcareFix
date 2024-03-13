package com.totalcarefix.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "technicians")
public class Technicians {
    @Id
   private int tech_id ;
   private int skill_id ;
}
