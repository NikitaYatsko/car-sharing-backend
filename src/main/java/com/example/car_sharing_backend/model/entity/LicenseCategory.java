package com.example.car_sharing_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "license_categories", schema = "carsharing-schema")
public class LicenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String code; // "A", "B", "C" и т.д.
}
