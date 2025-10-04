package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.mappers.UserMapper;
import com.example.car_sharing_backend.model.dto.UserRequestDTO;
import com.example.car_sharing_backend.model.dto.UserResponseDTO;
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


    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        log.info("Получение пользователя по id ={}", id);
        User user = userService.getUserById(id);

        UserResponseDTO userToResponse = UserMapper.INSTANCE.toUserResponseDTO(user);
        return ResponseEntity.ok(userToResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("Получен DTO: {}", userRequestDTO);
        // Маппим запрос -> сущность
        User userToSave = UserMapper.INSTANCE.fromUserRequestDTO(userRequestDTO);

        // Сохраняем пользователя и получаем объект с ID и датами
        User savedUser = userService.createUser(userToSave);
        log.info("Сохранение пользователя user = {}", savedUser);

        // Маппим сущность -> DTO ответа (без пароля)
        UserResponseDTO userToResponse = UserMapper.INSTANCE.toUserResponseDTO(savedUser);

        return ResponseEntity.ok(userToResponse);
    }
}
