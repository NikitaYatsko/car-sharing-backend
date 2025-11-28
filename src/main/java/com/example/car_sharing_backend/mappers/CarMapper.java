package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.response.CarResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarResponseDTO toCarDto(Car car){

        if(car == null){
            return null;
        }
        CarResponseDTO carResponseDTO = new CarResponseDTO();
        carResponseDTO.setModel(car.getModel());
        carResponseDTO.setStateNumber(car.getStateNumber());
        carResponseDTO.setType(car.getType());
        carResponseDTO.setPrice(car.getPrice());
        carResponseDTO.setLongitude(car.getLongitude());
        carResponseDTO.setLatitude(car.getLatitude());
        return carResponseDTO;
    }
}
