package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.exception.UserNotFoundException;
import com.example.car_sharing_backend.mappers.UserMapper;
import com.example.car_sharing_backend.model.dto.response.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.model.enums.ErrorMessage;
import com.example.car_sharing_backend.repository.UserRepository;
import com.example.car_sharing_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;



    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }


    @Override
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND_BY_ID.getMessage())
        );
        return userMapper.toDto(user);
    }
}
