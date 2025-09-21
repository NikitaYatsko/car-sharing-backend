package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.entity.User;

public interface UserService {
    User getUserById(Long id);
    User createUser(User user);
}
