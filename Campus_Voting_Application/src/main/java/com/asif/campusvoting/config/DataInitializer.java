package com.asif.campusvoting.config;

import com.asif.campusvoting.auth.entity.Role;
import com.asif.campusvoting.auth.entity.User;
import com.asif.campusvoting.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("admin@campus.com").isEmpty()) {

            User admin = User.builder()
                    .email("admin@campus.com")
                    .password(passwordEncoder.encode("Admin@123"))
                    .role(Role.ADMIN)
                    .verified(true)
                    .build();

            userRepository.save(admin);

            System.out.println("==================================");
            System.out.println("Default Admin Created");
            System.out.println("Email : admin@campus.com");
            System.out.println("Password : Admin@123");
            System.out.println("==================================");
        }
    }
}