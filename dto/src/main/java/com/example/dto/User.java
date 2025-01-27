package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String TIN;
    private Set<Car> cars;
    private Workshop workshop;
}
