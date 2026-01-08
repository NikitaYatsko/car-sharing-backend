package com.example.car_sharing_backend.repository;

import com.example.car_sharing_backend.model.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarImageRepository extends JpaRepository<CarImage, UUID> {
}
