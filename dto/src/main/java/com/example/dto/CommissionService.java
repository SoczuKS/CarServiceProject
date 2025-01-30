package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommissionService {
    private int id;
    private Commission commission;
    private Service service;
    private WorkStatus status;
}
