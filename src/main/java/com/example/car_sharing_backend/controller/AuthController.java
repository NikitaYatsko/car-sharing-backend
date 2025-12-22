package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.model.dto.request.JwtRequest;
import com.example.car_sharing_backend.model.dto.request.RegistrationUserRequest;
import com.example.car_sharing_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {

        return ResponseEntity.ok(authService.login(jwtRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserRequest registrationUserRequest) {
        return ResponseEntity
                .status(201)
                .body(authService.register(registrationUserRequest));

    }

}
