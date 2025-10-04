package com.example.car_sharing_backend.model.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String roles;
}
