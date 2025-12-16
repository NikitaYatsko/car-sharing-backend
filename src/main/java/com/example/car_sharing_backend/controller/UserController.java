package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.mappers.UserMapper;
import com.example.car_sharing_backend.model.dto.request.UserRequestDTO;
import com.example.car_sharing_backend.model.dto.response.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return null;
    }

}
