package com.example.car_sharing_backend.model.dto.response;

import com.example.car_sharing_backend.model.enums.DrivingLicenseRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class DrivingLicenseResponse {
    public DrivingLicenseRequestStatus status;
    public String message;


}
