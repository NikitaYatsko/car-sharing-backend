package com.example.car_sharing_backend.model.dto.request;

import com.example.car_sharing_backend.model.enums.DrivingLicenseRequestStatus;
import lombok.Data;

@Data
public class UpdateDrivingLicenseRequestDto {
    private DrivingLicenseRequestStatus status;
}
