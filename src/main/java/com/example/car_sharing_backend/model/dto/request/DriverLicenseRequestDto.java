package com.example.car_sharing_backend.model.dto.request;

import com.example.car_sharing_backend.model.dto.response.LicenseCategoryDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;


@Data
public class DriverLicenseRequestDto {
    private String licenseNumber;
    private LocalDate issuedDate;
    private LocalDate expiryDate;
    private String issuedBy;
    private Set<LicenseCategoryDto> categories;
}
