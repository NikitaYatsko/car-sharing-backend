package com.example.car_sharing_backend.repository;


import com.example.car_sharing_backend.model.entity.DrivingLicenseRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DrivingLicenseRequestRepository extends JpaRepository<DrivingLicenseRequestEntity, UUID> {
}
