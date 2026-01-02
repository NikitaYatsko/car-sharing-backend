package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.dto.request.DriverLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponseForAdmin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface DrivingLicenseRequestService {
    DrivingLicenseResponseForAdmin createDrivingLicenseRequest(DriverLicenseRequestDto drivingLicenseRequest, MultipartFile drivingLicensePhoto,MultipartFile selfiePhoto) throws IOException;
}
