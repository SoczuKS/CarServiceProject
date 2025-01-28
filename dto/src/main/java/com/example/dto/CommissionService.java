package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommissionService {
    private CommissionServiceId id;
    private Commission commission;
    private Service service;
    private WorkStatus status;
}
