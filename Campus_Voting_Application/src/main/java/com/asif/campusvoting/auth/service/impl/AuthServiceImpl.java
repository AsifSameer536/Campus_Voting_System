package com.asif.campusvoting.auth.service.impl;

import com.asif.campusvoting.auth.dto.LoginRequestDto;
import com.asif.campusvoting.auth.dto.LoginResponseDto;
import com.asif.campusvoting.auth.dto.RegisterRequestDto;
import com.asif.campusvoting.auth.dto.RegisterResponseDto;
import com.asif.campusvoting.auth.entity.Role;
import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.auth.repository.UserRepository;
import com.asif.campusvoting.auth.service.AuthService;
import com.asif.campusvoting.common.exception.InvalidEmailException;
import com.asif.campusvoting.common.exception.StudentAlreadyRegisteredException;
import com.asif.campusvoting.common.exception.StudentNotFoundException;
import com.asif.campusvoting.security.jwt.JwtService;
import com.asif.campusvoting.security.user.CustomUserDetails;
import com.asif.campusvoting.student.entity.StudentMaster;
import com.asif.campusvoting.student.repository.StudentMasterRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentMasterRepository studentMasterRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            StudentMasterRepository studentMasterRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.studentMasterRepository = studentMasterRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        StudentMaster student = studentMasterRepository
                .findByUsn(request.getUsn())
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found."));

        if (!student.getEmail().equalsIgnoreCase(request.getEmail())) {
            throw new InvalidEmailException(
                    "Email does not match college records."
            );
        }

        if (userRepository.existsByStudentMaster_Id(student.getId())) {
            throw new StudentAlreadyRegisteredException(
                    "Student is already registered."
            );
        }

        User user = User.builder()
                .studentMaster(student)
                .email(student.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .verified(false)
                .build();

        userRepository.save(user);

        return new RegisterResponseDto(
                "Registration successful. Please verify your email."
        );
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));

        String token = jwtService.generateToken(
                new CustomUserDetails(user)
        );

        return new LoginResponseDto(
                token,
                user.getEmail(),
                user.getRole().name()
        );
    }
}