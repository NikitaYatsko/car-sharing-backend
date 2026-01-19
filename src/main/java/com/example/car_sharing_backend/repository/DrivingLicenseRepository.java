package com.example.car_sharing_backend.repository;

import com.example.car_sharing_backend.model.entity.DrivingLicense;
import com.example.car_sharing_backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, UUID> {
    Optional<DrivingLicense> findByUser(User user);

}
