package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.dto.CarDTO;
import com.example.car_sharing_backend.model.entity.Car;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCars();

    CarDTO getCarById(Long id);

    CarDTO createCar(CarDTO carDTO);

    CarDTO updateCar(Long id, CarDTO carDTO);

    void deleteCar(Long id);
}
