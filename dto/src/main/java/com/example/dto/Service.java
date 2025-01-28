package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Service {
    private int id;
    private String name;
    private String description;
    private float cost;
    private Set<CommissionService> commissions;
}
