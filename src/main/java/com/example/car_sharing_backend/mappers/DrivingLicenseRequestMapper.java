package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.request.DriverLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponseForAdmin;
import com.example.car_sharing_backend.model.entity.DrivingLicenseRequestEntity;
import com.example.car_sharing_backend.model.entity.LicenseCategory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

import java.util.stream.Collectors;

@Component

public class DrivingLicenseRequestMapper {

    public DrivingLicenseRequestEntity toEntity(DriverLicenseRequestDto dto) {
        if (dto == null) {
            return null;
        }
        DrivingLicenseRequestEntity entity = new DrivingLicenseRequestEntity();

        entity.setCreatedAt(LocalDateTime.now());
        entity.setIssuedDate(dto.getIssuedDate());
        entity.setExpiryDate(dto.getExpiryDate());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setIssuedBy(dto.getIssuedBy());
        entity.setCategories(
                dto.getCategories().stream()
                        .map(c -> {
                            LicenseCategory cat = new LicenseCategory();
                            cat.setId(c.getId());
                            cat.setCode(c.getCode());
                            return cat;
                        })
                        .collect(Collectors.toSet())
        );

        return entity;

    }

    public DrivingLicenseResponseForAdmin toAdminDto(DrivingLicenseRequestEntity entity) {
        if (entity == null) return null;

        DrivingLicenseResponseForAdmin dto = new DrivingLicenseResponseForAdmin();

        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setUserFirstName(entity.getUser().getFirstName());
        dto.setUserLastName(entity.getUser().getLastName());

        dto.setLicenseNumber(entity.getLicenseNumber());
        dto.setIssuedDate(entity.getIssuedDate());
        dto.setExpiryDate(entity.getExpiryDate());
        dto.setIssuedBy(entity.getIssuedBy());

        dto.setUserSelfieUrl(entity.getUserSelfieUrl());
        dto.setLicensePhotoUrl(entity.getLicensePhotoUrl());
        dto.setRequestStatus(entity.getRequestStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setCategories(
                entity.getCategories().stream()
                        .map(DrivingLicenseMapper::toDto)
                        .collect(Collectors.toSet())
        );


        return dto;
    }
}


