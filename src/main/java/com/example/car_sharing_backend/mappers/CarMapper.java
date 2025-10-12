package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.CarResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CarMapper {

    CarResponseDTO carToCarResponseDTO(Car car);
}
