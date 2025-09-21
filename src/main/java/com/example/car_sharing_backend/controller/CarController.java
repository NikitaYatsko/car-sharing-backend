package com.example.car_sharing_backend.controller;

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
    public ResponseEntity<List<Car>> getAllCars() {
        log.info("Запрос на получение всех машин");
        List<Car> cars = carService.getAllCars();
        log.debug("Найдено {} машин", cars.size());
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    @Operation(
            summary = "Создать новую машину",
            description = "Добавляет новую машину в систему"
    )
    @ApiResponse(responseCode = "200", description = "Машина успешно создана")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        log.info("Запрос на создание новой машины: {}", car);
        Car createdCar = carService.createCar(car);
        log.info("Машина создана с ID={}", createdCar.getId());
        return ResponseEntity.ok(createdCar);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить машину по ID",
            description = "Возвращает информацию об одной машине по её ID"
    )
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        log.info("Запрос на получение машины с ID={}", id);
        Car car = carService.getCarById(id);
        log.debug("Найдена машина: {}", car);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить машину", description = "Обновляет данные по машине")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        log.info("Запрос на обновление машины с ID={} данными: {}", id, car);
        Car updatedCar = carService.updateCar(id, car);
        log.info("Машина с ID={} успешно обновлена", id);
        return ResponseEntity.ok(updatedCar);
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
