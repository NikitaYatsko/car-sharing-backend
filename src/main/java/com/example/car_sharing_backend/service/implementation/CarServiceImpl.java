package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.mapper.CarMapper;
import com.example.car_sharing_backend.model.dto.CarDTO;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.exception.CarNotFoundException;
import com.example.car_sharing_backend.repository.CarRepository;
import com.example.car_sharing_backend.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;


    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto) // конвертация через маппер
                .toList();
    }


    @Override
    public CarDTO getCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found"));
        return carMapper.toDto(car);

    }

    @Override
    public CarDTO createCar(CarDTO carDTO) {
        Car car = carMapper.toEntity(carDTO);
        Car carSaved = carRepository.save(car);
        return carMapper.toDto(carSaved);
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO carDto) {
        Car existing = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));

        existing.setModel(carDto.getModel());
        existing.setStateNumber(carDto.getStateNumber());
        existing.setType(carDto.getType());
        existing.setPrice(carDto.getPrice());
        existing.setStatus(carDto.getStatus());
        existing.setLatitude(carDto.getLatitude());
        existing.setLongitude(carDto.getLongitude());

        Car updated = carRepository.save(existing);
        return carMapper.toDto(updated);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
