package com.example.car_sharing_backend.model.dto.request;

import lombok.Data;

@Data
public class JwtRequest {
    private String username ;
    private String password;
}
