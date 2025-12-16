package com.example.car_sharing_backend.service.implementation;
import com.example.car_sharing_backend.repository.UserRepository;
import com.example.car_sharing_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



}
