package com.example.car_sharing_backend.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "license_categories", schema = "carsharing-schema")
public class LicenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String code; // "A", "B", "C" и т.д.
}
