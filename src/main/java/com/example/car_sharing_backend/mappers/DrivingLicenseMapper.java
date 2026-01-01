package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponse;
import com.example.car_sharing_backend.model.dto.response.LicenseCategoryResponse;
import com.example.car_sharing_backend.model.entity.DrivingLicense;
import com.example.car_sharing_backend.model.entity.LicenseCategory;

import java.util.Set;
import java.util.stream.Collectors;

public class DrivingLicenseMapper {


    public static LicenseCategoryResponse toDto(LicenseCategory category) {
        if (category == null) return null;
        LicenseCategoryResponse dto = new LicenseCategoryResponse();
        dto.setCode(category.getCode());
        return dto;
    }

    public static Set<LicenseCategoryResponse> toDto(Set<LicenseCategory> categories) {
        if (categories == null) return null;
        return categories.stream()
                .map(DrivingLicenseMapper::toDto)
                .collect(Collectors.toSet());
    }


    public static DrivingLicenseResponse toDto(DrivingLicense license) {
        if (license == null) return null;

        DrivingLicenseResponse dto = new DrivingLicenseResponse();

        dto.setLicenseNumber(license.getLicenseNumber());
        dto.setIssuedDate(license.getIssuedDate());
        dto.setExpiryDate(license.getExpiryDate());
        dto.setIssuedBy(license.getIssuedBy());

        dto.setCategories(toDto(license.getCategories()));

        return dto;
    }
}
