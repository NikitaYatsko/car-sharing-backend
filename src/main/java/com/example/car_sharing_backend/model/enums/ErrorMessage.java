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
    USER_DOES_NOT_EXIST("User doesn't exist"),
    TOKEN_EXPIRED("Token expired"),
    SIGNATURE_IS_NOT_VALID("Signature is not valid"),
    ;


    private String message;

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
