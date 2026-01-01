package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.RoleDto;
import com.example.car_sharing_backend.model.dto.response.UserProfileResponse;
import com.example.car_sharing_backend.model.dto.response.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.Role;
import com.example.car_sharing_backend.model.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserMapper {
    public RoleDto toRoleDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }

    public UserProfileResponse toProfileDto(User user) {
        if (user == null) {
            return null;
        }
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setYourUserId(user.getId());
        userProfileResponse.setUsername(user.getUsername());
        userProfileResponse.setEmail(user.getEmail());
        userProfileResponse.setRegistrationDate(user.getCreatedAt());
        userProfileResponse.setPhotoUrl(user.getAvatarUrl());
        userProfileResponse.setFirstName(user.getFirstName());
        userProfileResponse.setLastName(user.getLastName());
        userProfileResponse.setDrivingLicense(DrivingLicenseMapper.toDto(user.getDrivingLicense()));
        return userProfileResponse;
    }

    public UserResponseDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUuid(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUsername(user.getUsername());
        return userResponseDTO;
    }
}
