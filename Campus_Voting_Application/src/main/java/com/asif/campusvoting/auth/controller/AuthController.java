package com.asif.campusvoting.auth.controller;

import ch.qos.logback.core.CoreConstants;
import com.asif.campusvoting.auth.dto.LoginRequestDto;
import com.asif.campusvoting.auth.dto.LoginResponseDto;
import com.asif.campusvoting.auth.dto.RegisterRequestDto;
import com.asif.campusvoting.auth.dto.RegisterResponseDto;
import com.asif.campusvoting.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(
            @Valid @RequestBody RegisterRequestDto request) {

        RegisterResponseDto response = authService.register(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request) {

        System.out.println("Login API HIT");

        return ResponseEntity.ok(authService.login(request));
    }
}