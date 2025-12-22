package com.example.car_sharing_backend.model.dto.request;

import lombok.Data;

@Data
public class RegistrationUserRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

}
