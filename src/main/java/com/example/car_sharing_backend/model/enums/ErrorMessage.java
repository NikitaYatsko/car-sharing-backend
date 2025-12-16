package com.example.car_sharing_backend.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {
    CAR_NOT_FOUND_BY_ID("Car with id: %s not found"),
    CAR_ALREADY_RENTED("Car with id: %s already rented"),
    USER_NOT_FOUND_BY_ID("User with id: %s not found"),
    USERNAME_ALREADY_REGISTERED("Username already registered"),
    CAR_ALREADY_EXISTS("Car already exists"),
    ;


    private String message;

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
