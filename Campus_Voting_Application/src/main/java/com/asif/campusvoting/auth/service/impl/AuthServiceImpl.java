package com.asif.campusvoting.auth.service.impl;

import com.asif.campusvoting.auth.dto.LoginRequestDto;
import com.asif.campusvoting.auth.dto.LoginResponseDto;
import com.asif.campusvoting.auth.dto.RegisterRequestDto;
import com.asif.campusvoting.auth.dto.RegisterResponseDto;
import com.asif.campusvoting.auth.repository.UserRepository;
import com.asif.campusvoting.auth.service.AuthService;
import com.asif.campusvoting.common.exception.InvalidCredentialsException;
import com.asif.campusvoting.common.exception.InvalidEmailException;
import com.asif.campusvoting.common.exception.StudentAlreadyRegisteredException;
import com.asif.campusvoting.common.exception.StudentNotFoundException;
import com.asif.campusvoting.student.entity.StudentMaster;
import com.asif.campusvoting.student.repository.StudentMasterRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.auth.entity.Role;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentMasterRepository studentMasterRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           StudentMasterRepository studentMasterRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentMasterRepository = studentMasterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        StudentMaster student = studentMasterRepository
                .findByUsn(request.getUsn())
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found."));
        if (!student.getEmail().equalsIgnoreCase(request.getEmail())) {
            throw new InvalidEmailException("Email does not match college records.");
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

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }
        return new LoginResponseDto("Login successful");
    }
}