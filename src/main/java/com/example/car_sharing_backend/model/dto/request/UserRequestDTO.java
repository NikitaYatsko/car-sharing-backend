package com.example.car_sharing_backend.model.dto.request;

import lombok.Data;



@Data
public class UserRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
