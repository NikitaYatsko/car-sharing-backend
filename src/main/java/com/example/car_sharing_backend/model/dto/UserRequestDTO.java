package com.example.car_sharing_backend.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate createDate;
}
