package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.request.NewCarRequest;
import com.example.car_sharing_backend.model.dto.response.CarResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.model.enums.CarStatus;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarResponseDTO toCarDto(Car car) {

        if (car == null) {
            return null;
        }
        CarResponseDTO carResponseDTO = new CarResponseDTO();
        carResponseDTO.setModel(car.getModel());
        carResponseDTO.setStateNumber(car.getStateNumber());
        carResponseDTO.setType(car.getType());
        carResponseDTO.setPrice(car.getPrice());
        carResponseDTO.setLongitude(car.getLongitude());
        carResponseDTO.setLatitude(car.getLatitude());
        carResponseDTO.setStatus(String.valueOf(car.getStatus()));
        return carResponseDTO;
    }

    public Car UpdateCar(Car car) {
        if (car == null) {
            return null;
        }
        Car existing = new Car();
        existing.setModel(car.getModel());
        existing.setStateNumber(car.getStateNumber());
        existing.setType(car.getType());
        existing.setPrice(car.getPrice());
        existing.setStatus(car.getStatus());
        existing.setLatitude(car.getLatitude());
        existing.setLongitude(car.getLongitude());
        return existing;
    }

    public Car toEntity(NewCarRequest request) {
        Car car = new Car();
        car.setModel(request.getModel());
        car.setStateNumber(request.getStateNumber());
        car.setType(request.getType());
        car.setPrice(request.getPrice());
        return car;
    }
}
