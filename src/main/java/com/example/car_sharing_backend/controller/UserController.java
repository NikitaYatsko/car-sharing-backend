package com.example.car_sharing_backend.controller;
import com.example.car_sharing_backend.model.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/info")
    public String getUserInfo(Principal principal) {
        return principal.getName();
    }

}
