package com.example.car_sharing_backend.repository;

import com.example.car_sharing_backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
