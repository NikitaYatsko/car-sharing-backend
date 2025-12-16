package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.mappers.CarMapper;
import com.example.car_sharing_backend.model.dto.request.NewCarRequest;
import com.example.car_sharing_backend.model.dto.request.UpdateCarDto;
import com.example.car_sharing_backend.model.dto.response.CarResponseDTO;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    @Operation(
            summary = "Создать новую машину",
            description = "Добавляет новую машину в систему"
    )
    @ApiResponse(responseCode = "200", description = "Машина успешно создана")
    public ResponseEntity<CarResponseDTO> createCar(@RequestBody NewCarRequest request) {
        log.info("Запрос на создание новой машины: {}", request);

        return ResponseEntity.ok(carService.createCar(request));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить машину по ID",
            description = "Возвращает информацию об одной машине по её ID"
    )
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable UUID id) {
        log.info("Запрос на получение машины с ID={}", id);
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить машину", description = "Обновляет данные по машине")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable UUID id, @RequestBody UpdateCarDto car) {
        log.info("Запрос на обновление машины с ID={} данными: {}", id, car);
        return ResponseEntity.ok(carService.updateCar(id, car));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить машину", description = "Удаляет машину по ID")
    public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        log.warn("Запрос на удаление машины с ID={}", id);
        carService.deleteCar(id);
        log.info("Машина с ID={} успешно удалена", id);
        return ResponseEntity.noContent().build();
    }
}
