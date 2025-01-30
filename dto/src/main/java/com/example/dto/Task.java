package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Task {
    private int id;
    private String name;
    private float cost;
    private Set<Service> services;
}
