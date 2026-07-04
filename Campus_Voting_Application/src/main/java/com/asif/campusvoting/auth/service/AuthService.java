package com.asif.campusvoting.auth.service;

import com.asif.campusvoting.auth.dto.LoginRequestDto;
import com.asif.campusvoting.auth.dto.LoginResponseDto;
import com.asif.campusvoting.auth.dto.RegisterRequestDto;
import com.asif.campusvoting.auth.dto.RegisterResponseDto;

public interface AuthService {

    RegisterResponseDto register(RegisterRequestDto request);
    LoginResponseDto login(LoginRequestDto request);

}