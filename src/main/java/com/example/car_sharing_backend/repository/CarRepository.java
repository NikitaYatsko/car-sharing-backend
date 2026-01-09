package com.example.car_sharing_backend.repository;

import com.example.car_sharing_backend.model.entity.Car;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    boolean existsByStateNumber(String stateNumber);

    @Override
    @EntityGraph(attributePaths = "images")
    List<Car> findAll();
}
