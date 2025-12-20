package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.service.implementation.UserService;
import com.example.security.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;

}
