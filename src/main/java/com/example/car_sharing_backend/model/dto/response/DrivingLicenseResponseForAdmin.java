package com.example.car_sharing_backend.model.dto.response;

import com.example.car_sharing_backend.model.enums.DrivingLicenseRequestStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class DrivingLicenseResponseForAdmin {
    private UUID id;
    private UUID userId;
    private String userFirstName;
    private String userLastName;
    private String licenseNumber;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private String issuedBy;
    private String userSelfieUrl;
    private String licensePhotoUrl;
    private DrivingLicenseRequestStatus requestStatus;
    private LocalDateTime createdAt;
    private Set<LicenseCategoryDto> categories;
}
