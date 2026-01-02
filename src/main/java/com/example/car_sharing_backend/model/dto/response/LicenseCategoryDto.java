package com.example.car_sharing_backend.model.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class LicenseCategoryDto {
    private UUID id;
    private String code;
}
