package com.example.car_sharing_backend.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class JwtResponse {
    private String username;
    private String token;
}
