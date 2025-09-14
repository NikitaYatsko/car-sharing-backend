package com.example.car_sharing_backend.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars", schema = "carsharing-schema")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный ID машины", example = "1")
    private Long id;

    @NotBlank(message = "Модель автомобиля не может быть пустой")
    @Column(nullable = false)
    @Schema(description = "Модель автомобиля", example = "Toyota Corolla")
    private String model;

    @NotBlank(message = "Госномер не может быть пустым")
    @Size(max = 20, message = "Госномер слишком длинный")
    @Column(nullable = false, unique = true)
    @Schema(description = "Госномер", example = "AB1234CD")
    private String stateNumber;

    @NotBlank(message = "Тип автомобиля не может быть пустым")
    @Column(nullable = false)
    @Schema(description = "Тип автомобиля", example = "sedan")
    private String type;

    @NotNull(message = "Цена не может быть пустой")
    @Positive(message = "Цена должна быть положительной")
    @Column(nullable = false)
    @Schema(description = "Цена аренды в день", example = "3500")
    private Integer price;

    @NotBlank(message = "Статус не может быть пустым")
    @Column(nullable = false)
    @Schema(description = "Статус (available, rented, maintenance)", example = "available")
    private String status;

    @Column
    @Schema(description = "Обновление даты при изменениях", example = "2025-09-14 08:51:23.324673")
    @UpdateTimestamp
    private LocalDateTime updated_at;

    @Column
    @Schema(description = "Дата создания записи", example = "2025-09-14 08:51:23.324673")
    @CreationTimestamp
    private LocalDateTime created_at;

    @NotNull(message = "Широта не может быть пустой")
    @DecimalMin(value = "-90.0", message = "Широта должна быть >= -90")
    @DecimalMax(value = "90.0", message = "Широта должна быть <= 90")
    @Schema(description = "Широта", example = "47.0105")
    @Column(nullable = false)
    private Double latitude;

    @NotNull(message = "Долгота не может быть пустой")
    @DecimalMin(value = "-180.0", message = "Долгота должна быть >= -180")
    @DecimalMax(value = "180.0", message = "Долгота должна быть <= 180")
    @Schema(description = "Долгота", example = "28.8638")
    @Column(nullable = false)
    private Double longitude;
}
