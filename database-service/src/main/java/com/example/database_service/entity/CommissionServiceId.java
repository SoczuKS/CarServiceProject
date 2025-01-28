package com.example.database_service.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CommissionServiceId implements Serializable {
    private int commissionId;
    private int serviceId;
}
