package com.example.car_sharing_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "driving_licenses", schema = "carsharing-schema")
public class DrivingLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;
    @Column(name = "license_number", unique = true)
    private String licenseNumber;
    @Column(name = "issued_date")
    private LocalDate issuedDate;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column(name = "issued_by")
    private String issuedBy;
    @ManyToMany
    @JoinTable(
            name = "driving_license_categories",
            schema = "carsharing-schema",
            joinColumns = @JoinColumn(name = "driving_license_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<LicenseCategory> categories = new HashSet<>();

}
