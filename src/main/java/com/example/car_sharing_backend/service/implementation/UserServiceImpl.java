package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.exception.UserAlreadyExistsException;
import com.example.car_sharing_backend.exception.UserNotFoundException;
import com.example.car_sharing_backend.model.dto.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.repository.UserRepository;
import com.example.car_sharing_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User createUser(User user) {


        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }
}
