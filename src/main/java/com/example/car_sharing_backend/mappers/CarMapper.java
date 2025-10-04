package com.example.car_sharing_backend.mappers;

import com.example.car_sharing_backend.model.dto.CarResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarResponseDTO carToCarResponseDTO(Car car);
}
