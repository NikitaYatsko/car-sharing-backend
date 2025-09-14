package com.example.car_sharing_backend.mapper;

import com.example.car_sharing_backend.model.dto.CarDTO;
import com.example.car_sharing_backend.model.entity.Car;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")

public interface CarMapper {

    Car toEntity(CarDTO Cardto);

    CarDTO toDto(Car car);

}
