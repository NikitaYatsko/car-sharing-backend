package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.exception.CarNotFoundException;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.repository.CarRepository;
import com.example.car_sharing_backend.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        log.info("Получение списка всех машин");
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        log.info("Получение машины с id={}", id);
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
    }

    @Override
    public Car createCar(Car car) {
        log.info("Создание новой машины: {}", car);
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        log.info("Обновление машины с id={}", id);

        Car existing = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));

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
        log.info("Удаление машины с id={}", id);
        Car car = getCarById(id);
        carRepository.deleteById(car.getId());
    }
}
