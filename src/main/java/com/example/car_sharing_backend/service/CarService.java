package com.example.car_sharing_backend.service;


import com.example.car_sharing_backend.model.dto.request.NewCarRequest;
import com.example.car_sharing_backend.model.dto.request.UpdateCarDto;
import com.example.car_sharing_backend.model.dto.response.CarResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<CarResponseDTO> getAllCars();

    CarResponseDTO getCarById(UUID id);

    CarResponseDTO createCar(NewCarRequest car);

    CarResponseDTO updateCar(UUID id, UpdateCarDto car);

    void deleteCar(UUID id);
}
