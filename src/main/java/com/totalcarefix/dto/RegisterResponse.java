package com.totalcarefix.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {

    String message;
    boolean state;
    String role;
}
