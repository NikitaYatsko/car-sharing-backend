package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.UserRequestDTO;
import com.example.car_sharing_backend.model.dto.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromUserRequestDTO(UserRequestDTO dto);

    UserResponseDTO toUserResponseDTO(User user);
}
