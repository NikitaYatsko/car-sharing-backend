package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.model.dto.CarDTO;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    @Operation(
            summary = "Создать новую машину",
            description = "Добавляет новую машину в систему"
    )
    @ApiResponse(responseCode = "200", description = "Машина успешно создана")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.createCar(carDTO));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить машину по ID",
            description = "Возвращает информацию об одной машине по её ID"
    )
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Обновить машину", description = "Обновляет данные по машине")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(id, carDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить машину", description = "Удаляет машину по ID")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
