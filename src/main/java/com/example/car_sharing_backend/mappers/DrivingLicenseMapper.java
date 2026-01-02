package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.response.DrivingLicenseDto;
import com.example.car_sharing_backend.model.dto.response.LicenseCategoryDto;
import com.example.car_sharing_backend.model.entity.DrivingLicense;
import com.example.car_sharing_backend.model.entity.LicenseCategory;

import java.util.Set;
import java.util.stream.Collectors;

public class DrivingLicenseMapper {


    public static LicenseCategoryDto toDto(LicenseCategory category) {
        if (category == null) return null;
        LicenseCategoryDto dto = new LicenseCategoryDto();
        dto.setId(category.getId());
        dto.setCode(category.getCode());
        return dto;
    }

    public static Set<LicenseCategoryDto> toDto(Set<LicenseCategory> categories) {
        if (categories == null) return null;
        return categories.stream()
                .map(DrivingLicenseMapper::toDto)
                .collect(Collectors.toSet());
    }


    public static DrivingLicenseDto toDto(DrivingLicense license) {
        if (license == null) return null;

        DrivingLicenseDto dto = new DrivingLicenseDto();

        dto.setLicenseNumber(license.getLicenseNumber());
        dto.setIssuedDate(license.getIssuedDate());
        dto.setExpiryDate(license.getExpiryDate());
        dto.setIssuedBy(license.getIssuedBy());

        dto.setCategories(toDto(license.getCategories()));

        return dto;
    }
}
