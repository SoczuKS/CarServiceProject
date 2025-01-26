package com.example.database_service;

import com.example.database_service.entity.Role;
import com.example.database_service.entity.User;
import com.example.database_service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@ip.pl")) {
            User admin = new User();
            admin.setEmail("admin@ip.pl");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setFirstName("Admin");
            admin.setLastName("IP");
            admin.setRole(Role.ADMIN);
            admin.setCreatedAt(LocalDateTime.now());
            admin.setUpdatedAt(LocalDateTime.now());
            admin.setActive(true);
            userRepository.save(admin);
        }
    }
}
