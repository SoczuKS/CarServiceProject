package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Commission {
    private int id;
    private Set<Service> services;
}
