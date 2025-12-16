package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.exception.CarAlreadyExistsException;
import com.example.car_sharing_backend.exception.CarNotFoundException;
import com.example.car_sharing_backend.mappers.CarMapper;
import com.example.car_sharing_backend.model.dto.request.NewCarRequest;
import com.example.car_sharing_backend.model.dto.response.CarResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.model.enums.CarStatus;
import com.example.car_sharing_backend.model.enums.ErrorMessage;
import com.example.car_sharing_backend.repository.CarRepository;
import com.example.car_sharing_backend.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarResponseDTO> getAllCars() {
        log.info("Получение списка всех машин");
        return carRepository.findAll()
                .stream()
                .map(carMapper::toCarDto)
                .toList();
    }

    @Override
    public CarResponseDTO getCarById(UUID id) {
        log.info("Получение машины с id={}", id);
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(ErrorMessage.CAR_NOT_FOUND_BY_ID.getMessage()));
        return carMapper.toCarDto(car);
    }

    @Override
    public CarResponseDTO createCar(NewCarRequest request) {
        Car car = carMapper.toEntity(request);
        if (carRepository.existsByStateNumber(car.getStateNumber())) {
            throw new CarAlreadyExistsException(ErrorMessage.CAR_ALREADY_EXISTS.getMessage());
        }

        if (car.getStatus() == null) {
            car.setStatus(CarStatus.AVAILABLE);
        }
        carRepository.save(car);

        return carMapper.toCarDto(car);
    }

    @Override
    public CarResponseDTO updateCar(UUID id, Car car) {
        log.info("Обновление машины с id={}", id);
        Car existing = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(ErrorMessage.CAR_NOT_FOUND_BY_ID.getMessage()));
        Car updatedCar = carMapper.UpdateCar(existing);
        carRepository.save(updatedCar);
        return carMapper.toCarDto(updatedCar);
    }

    @Override
    public void deleteCar(UUID id) {
        log.info("Удаление машины с id={}", id);

        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException(
                    ErrorMessage.CAR_NOT_FOUND_BY_ID.getMessage()
            );
        }

        carRepository.deleteById(id);
    }

}
