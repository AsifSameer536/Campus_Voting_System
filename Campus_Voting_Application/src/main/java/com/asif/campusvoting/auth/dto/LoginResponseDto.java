package com.asif.campusvoting.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private String token;
    private String email;
    private String role;
}