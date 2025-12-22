package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.dto.request.JwtRequest;
import com.example.car_sharing_backend.model.dto.request.RegistrationUserRequest;
import com.example.car_sharing_backend.model.dto.response.JwtResponse;

public interface AuthService {
  JwtResponse login(JwtRequest jwtRequest);
  JwtResponse register(RegistrationUserRequest registrationUserRequest);
}
