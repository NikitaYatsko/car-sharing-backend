package com.example.car_sharing_backend.exception;

public class DrivingLicenseNotFoundException extends RuntimeException {
    public DrivingLicenseNotFoundException(String message) {
        super(message);
    }
}
