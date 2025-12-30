package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.dto.response.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponseDTO> getUsers();
    UserResponseDTO getUserById(UUID id);
}
