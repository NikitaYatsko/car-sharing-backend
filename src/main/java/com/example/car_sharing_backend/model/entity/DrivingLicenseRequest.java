package com.example.car_sharing_backend.model.entity;

import com.example.car_sharing_backend.model.enums.DrivingLicenseRequestStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "driving_license_request", schema = "carsharing-schema")
public class DrivingLicenseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "license_number", length = 30)
    private String licenseNumber;

    @Column(name = "issued_date")
    private LocalDate issuedDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "issued_by", length = 100)
    private String issuedBy;

    @Column(name = "user_selfie_url", columnDefinition = "text")
    private String userSelfieUrl;

    @Column(name = "license_photo_url", columnDefinition = "text")
    private String licensePhotoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private DrivingLicenseRequestStatus requestStatus = DrivingLicenseRequestStatus.PENDING;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
