package com.example.car_sharing_backend.service;


import com.example.car_sharing_backend.model.dto.request.CarData;
import com.example.car_sharing_backend.model.dto.request.UpdateCarDto;
import com.example.car_sharing_backend.model.dto.response.CarResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CarService {
    List<CarResponseDTO> getAllCars();

    CarResponseDTO getCarById(UUID id);

    CarResponseDTO createCar(List<MultipartFile> request, CarData data) throws IOException;

    CarResponseDTO updateCar(UUID id, UpdateCarDto car);

    void deleteCar(UUID id);
}
