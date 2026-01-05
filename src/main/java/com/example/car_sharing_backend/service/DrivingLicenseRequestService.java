package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.dto.request.DriverLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.request.UpdateDrivingLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponse;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponseForAdmin;
import com.example.car_sharing_backend.service.implementation.DrivingLicenseRequestServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


public interface DrivingLicenseRequestService {
    DrivingLicenseResponseForAdmin createDrivingLicenseRequest(DriverLicenseRequestDto drivingLicenseRequest, MultipartFile drivingLicensePhoto,MultipartFile selfiePhoto) throws IOException;
    DrivingLicenseResponseForAdmin getDrivingLicenseById(UUID id);
    DrivingLicenseResponse approveOrDenyRequest(UUID id, UpdateDrivingLicenseRequestDto dto);
}
