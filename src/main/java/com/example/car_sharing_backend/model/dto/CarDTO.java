package com.example.car_sharing_backend.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO")
public class CarDTO {

    @NotBlank(message = "Модель автомобиля не может быть пустой")

    @Schema(description = "Модель автомобиля", example = "Toyota Corolla")
    private String model;

    @NotBlank(message = "Госномер не может быть пустым")
    @Size(max = 20, message = "Госномер слишком длинный")

    @Schema(description = "Госномер", example = "AB1234CD")
    private String stateNumber;

    @NotBlank(message = "Тип автомобиля не может быть пустым")

    @Schema(description = "Тип автомобиля", example = "sedan")
    private String type;

    @NotNull(message = "Цена не может быть пустой")
    @Positive(message = "Цена должна быть положительной")

    @Schema(description = "Цена аренды в день", example = "3500")
    private Integer price;

    @NotBlank(message = "Статус не может быть пустым")

    @Schema(description = "Статус (available, rented, maintenance)", example = "available")
    private String status;

    @NotNull(message = "Широта не может быть пустой")
    @DecimalMin(value = "-90.0", message = "Широта должна быть >= -90")
    @DecimalMax(value = "90.0", message = "Широта должна быть <= 90")
    @Schema(description = "Широта", example = "47.0105")

    private Double latitude;

    @NotNull(message = "Долгота не может быть пустой")
    @DecimalMin(value = "-180.0", message = "Долгота должна быть >= -180")
    @DecimalMax(value = "180.0", message = "Долгота должна быть <= 180")
    @Schema(description = "Долгота", example = "28.8638")

    private Double longitude;
}
