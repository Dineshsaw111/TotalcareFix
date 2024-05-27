package com.totalcarefix.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private  String email;
    private  String role;
}
