package com.example.car_sharing_backend.model.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;


@Data
public class DrivingLicenseResponse {
    private String licenseNumber;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private String issuedBy;
    private Set<LicenseCategoryResponse> categories;
}
