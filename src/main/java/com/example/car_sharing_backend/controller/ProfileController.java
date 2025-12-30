package com.example.car_sharing_backend.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.car_sharing_backend.exception.UserNotFoundException;
import com.example.car_sharing_backend.mappers.UserMapper;
import com.example.car_sharing_backend.model.dto.response.UserProfileResponse;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.model.enums.ErrorMessage;
import com.example.car_sharing_backend.repository.UserRepository;
import com.example.car_sharing_backend.service.implementation.UserServiceDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UserServiceDetails userServiceDetails;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final Cloudinary cloudinary;

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getCurrentUserProfile(Authentication authentication) {
        User user = getCurrentUser(authentication);
        return ResponseEntity.ok(userMapper.toProfileDto(user));
    }

    @PostMapping("/photo")
    public ResponseEntity<Map<String, String>> uploadPhoto(@RequestParam("file") MultipartFile file,
                                                           Authentication authentication) {
        try {
            User user = getCurrentUser(authentication);

            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("folder", "user_photos")
            );

            String photoUrl = (String) uploadResult.get("secure_url");
            user.setAvatarUrl(photoUrl);
            userRepository.save(user);

            return ResponseEntity.ok(Map.of("photoUrl", photoUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Failed to upload photo"));
        }
    }

    // приватный метод для получения текущего пользователя
    private User getCurrentUser(Authentication authentication) {
        return userServiceDetails.findByUsername(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException(
                        ErrorMessage.USER_NOT_FOUND_BY_USERNAME.getMessage()
                ));
    }
}
