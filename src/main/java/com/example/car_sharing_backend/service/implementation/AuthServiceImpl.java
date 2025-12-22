package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.model.dto.request.JwtRequest;
import com.example.car_sharing_backend.model.dto.request.RegistrationUserRequest;
import com.example.car_sharing_backend.model.dto.response.JwtResponse;
import com.example.car_sharing_backend.model.entity.Role;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.model.enums.ErrorMessage;
import com.example.car_sharing_backend.repository.RoleRepository;
import com.example.car_sharing_backend.repository.UserRepository;
import com.example.car_sharing_backend.security.JwtTokenUtils;
import com.example.car_sharing_backend.service.AuthService;
import com.example.car_sharing_backend.exception.RoleNotFoundException;
import com.example.car_sharing_backend.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(ErrorMessage.BAD_CREDENTIALS.getMessage());
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponse(userDetails.getUsername(), token, roles);
    }

    @Override
    public JwtResponse register(RegistrationUserRequest registrationUserRequest) {

        if (userService.findByUsername(registrationUserRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(ErrorMessage.USERNAME_ALREADY_REGISTERED.getMessage());
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RoleNotFoundException(ErrorMessage.ROLE_NOT_FOUND.getMessage()));


        User user = new User();
        user.setUsername(registrationUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUserRequest.getPassword()));
        user.setEmail(registrationUserRequest.getEmail());
        user.setRoles(Set.of(role));
        userRepository.save(user);

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponse(user.getUsername(), token, roles);
    }
}
