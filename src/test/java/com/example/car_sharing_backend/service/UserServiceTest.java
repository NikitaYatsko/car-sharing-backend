package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.exception.UserNotFoundException;
import com.example.car_sharing_backend.mappers.UserMapper;
import com.example.car_sharing_backend.model.dto.RoleDto;
import com.example.car_sharing_backend.model.dto.response.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.Role;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.repository.RoleRepository;
import com.example.car_sharing_backend.repository.UserRepository;
import com.example.car_sharing_backend.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private Role testRole;
    private UserResponseDTO userResponseDTO;
    private UUID userId = UUID.randomUUID();
    private static final UUID NON_EXISTING_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @BeforeEach
    void setUp() {

        // Создаем роль
        testRole = new Role();
        testRole.setName("USER");

        // Создаем пользователя
        testUser = new User();
        testUser.setId(userId);
        testUser.setUsername("testuser");
        testUser.setEmail("testemail@gmail.com");
        testUser.setRoles(Set.of(testRole));


        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUuid(userId);
        userResponseDTO.setUsername("testuser");
        userResponseDTO.setEmail("testemail@gmail.com");
    }

    @Test
    void getById_UserExists_ReturnsUserDto() {

        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));
        when(userMapper.toDto(testUser)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(testUser.getUsername(), result.getUsername());
        assertEquals(testUser.getEmail(), result.getEmail());
        assertEquals(testUser.getId(), result.getUuid());

        verify(userRepository).findById(userId);
        verify(userMapper).toDto(testUser);
    }

    @Test
    void getById_UserNotFound_ThrowsException() {
        when(userRepository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(NON_EXISTING_ID))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("not found");

        verify(userRepository, times(1)).findById(NON_EXISTING_ID);
        verify(userMapper, times(0)).toDto(testUser);
    }
}
