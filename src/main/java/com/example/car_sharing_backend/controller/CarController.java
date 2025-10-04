package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.mappers.CarMapper;
import com.example.car_sharing_backend.model.dto.CarResponseDTO;
import com.example.car_sharing_backend.model.dto.UserResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;


    @GetMapping
    @Operation(
            summary = "Получить все машины",
            description = "Возвращает список всех доступных машин"
    )
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        log.info("Запрос на получение всех машин");
        List<Car> cars = carService.getAllCars();
        List<CarResponseDTO> dtoCars = cars.stream()
                .map(CarMapper.INSTANCE::carToCarResponseDTO)
                .toList();
        log.debug("Найдено {} машин", cars.size());
        return ResponseEntity.ok(dtoCars);
    }


    @PostMapping
    @Operation(
            summary = "Создать новую машину",
            description = "Добавляет новую машину в систему"
    )
    @ApiResponse(responseCode = "200", description = "Машина успешно создана")
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody Car car) {
        log.info("Запрос на создание новой машины: {}", car);
        Car createdCar = carService.createCar(car);
        CarResponseDTO carResponseDTO = CarMapper.INSTANCE.carToCarResponseDTO(createdCar);
        log.info("Машина создана с ID={}", createdCar.getId());
        return ResponseEntity.ok(carResponseDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить машину по ID",
            description = "Возвращает информацию об одной машине по её ID"
    )
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Long id) {
        log.info("Запрос на получение машины с ID={}", id);
        Car car = carService.getCarById(id);
        CarResponseDTO carResponseDTO = CarMapper.INSTANCE.carToCarResponseDTO(car);
        log.debug("Найдена машина: {}", car);
        return ResponseEntity.ok(carResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить машину", description = "Обновляет данные по машине")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long id, @RequestBody Car car) {
        log.info("Запрос на обновление машины с ID={} данными: {}", id, car);
        Car updatedCar = carService.updateCar(id, car);
        CarResponseDTO carResponseDTO = CarMapper.INSTANCE.carToCarResponseDTO(updatedCar);
        log.info("Машина с ID={} успешно обновлена", id);
        return ResponseEntity.ok(carResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить машину", description = "Удаляет машину по ID")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        log.warn("Запрос на удаление машины с ID={}", id);
        carService.deleteCar(id);
        log.info("Машина с ID={} успешно удалена", id);
        return ResponseEntity.noContent().build();
    }
}
