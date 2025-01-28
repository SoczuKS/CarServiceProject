package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Car {
    private int id;
    private String brand;
    private String model;
    private User owner;
    private int year;
    private Set<Commission> commissions;
}
