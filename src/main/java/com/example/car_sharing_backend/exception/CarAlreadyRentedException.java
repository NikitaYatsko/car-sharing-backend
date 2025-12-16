package com.example.car_sharing_backend.exception;

public class CarAlreadyRentedException extends RuntimeException {
    public CarAlreadyRentedException(String message) {
        super(message);
    }
}
