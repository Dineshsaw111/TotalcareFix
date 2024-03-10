package com.totalcarefix.DTO;

import jakarta.persistence.Entity;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class requestRolesDTO {
        private String name;
    }

