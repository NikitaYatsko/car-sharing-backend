package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.entity.Car;
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

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
    }

    @Override
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car existing = getCarById(id);
        existing.setModel(car.getModel());
        existing.setStateNumber(car.getStateNumber());
        existing.setType(car.getType());
        existing.setPrice(car.getPrice());
        existing.setStatus(car.getStatus());
        existing.setLatitude(car.getLatitude());
        existing.setLongitude(car.getLongitude());
        return carRepository.save(existing);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
