package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "commission_services")
@Getter
@Setter
public class CommissionService {
    @EmbeddedId
    private CommissionServiceId id;

    @ManyToOne
    @MapsId("commissionId")
    @JoinColumn(name = "commission_id")
    @JsonIgnoreProperties("services")
    private Commission commission;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    @JsonIgnoreProperties("commissions")
    private Service service;

    private WorkStatus status;
}
