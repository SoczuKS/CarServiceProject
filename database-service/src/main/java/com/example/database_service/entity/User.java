package com.example.database_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "email", columnList = "email"),
                @Index(name = "role", columnList = "role")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "email", columnNames = {"email"})
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private String TIN;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties(value = {"owner"}, allowSetters = true)
    private Set<Car> cars;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties(value = {"employees"}, allowSetters = true)
    private Workshop workshop;

    @OneToMany(mappedBy = "mechanic")
    @JsonIgnoreProperties(value = {"mechanic"}, allowSetters = true)
    private Set<Commission> commissions;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
