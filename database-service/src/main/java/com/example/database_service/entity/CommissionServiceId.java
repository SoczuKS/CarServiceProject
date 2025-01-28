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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommissionServiceId that)) return false;
        return this.getCommissionId() == that.getCommissionId() && this.getServiceId() == that.getServiceId();
    }

    @Override
    public int hashCode() {
        return ((commissionId + serviceId) * (commissionId + serviceId + 1)) / 2 + serviceId;
    }
}
