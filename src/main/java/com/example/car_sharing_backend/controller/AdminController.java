package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.model.dto.request.UpdateDrivingLicenseRequestDto;
import com.example.car_sharing_backend.model.dto.response.DrivingLicenseResponseForAdmin;
import com.example.car_sharing_backend.service.implementation.DrivingLicenseRequestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final DrivingLicenseRequestServiceImpl drivingLicenseRequestServiceImpl;

    @GetMapping("/driving-license-requests/{id}")
    public ResponseEntity<DrivingLicenseResponseForAdmin> getDrivingLicenseRequestById(@PathVariable UUID id) {
        DrivingLicenseResponseForAdmin response = drivingLicenseRequestServiceImpl.getDrivingLicenseById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/driving-license-requests/{id}")
    public ResponseEntity<DrivingLicenseRequestServiceImpl.DrivingLicenseResponse> approveOrDenyRequest(@PathVariable UUID id, @RequestBody UpdateDrivingLicenseRequestDto dto) {
        drivingLicenseRequestServiceImpl.approveOrDenyRequest(id, dto);
        return ResponseEntity.ok().build();
    }
}
