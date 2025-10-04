package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.UserRequestDTO;
import com.example.car_sharing_backend.model.dto.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User fromUserRequestDTO(UserRequestDTO dto);

    UserResponseDTO toUserResponseDTO(User user);
}
