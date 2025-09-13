package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(Long id);

    Car createCar(Car car);

    Car updateCar(Long id, Car car);

    void deleteCar(Long id);
}
