package com.example.car_sharing_backend.service;


import com.example.car_sharing_backend.model.dto.request.NewCarRequest;
import com.example.car_sharing_backend.model.dto.response.CarResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;

import java.util.List;

public interface CarService {
    List<CarResponseDTO> getAllCars();

    CarResponseDTO getCarById(Long id);

    CarResponseDTO createCar(NewCarRequest car);

    CarResponseDTO updateCar(Long id, Car car);

    void deleteCar(Long id);
}
