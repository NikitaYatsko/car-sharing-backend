package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.exception.UserNotFoundException;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.model.enums.ErrorMessage;
import com.example.car_sharing_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CurrentUserService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_DOES_NOT_EXIST.getMessage()));
    }
}
