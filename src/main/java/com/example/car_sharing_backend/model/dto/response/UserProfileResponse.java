package com.example.car_sharing_backend.model.dto.response;

import lombok.Data;


import java.time.LocalDateTime;

import java.util.UUID;

@Data
public class UserProfileResponse {
    private UUID yourUserId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private DrivingLicenseDto drivingLicense;
    private LocalDateTime registrationDate;
    private String photoUrl;
}
