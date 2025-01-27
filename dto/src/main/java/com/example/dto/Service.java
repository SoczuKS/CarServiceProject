package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Service {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private Set<User> employees;
}
