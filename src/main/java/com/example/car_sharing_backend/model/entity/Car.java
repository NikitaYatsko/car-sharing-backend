package com.example.car_sharing_backend.model.entity;

import com.example.car_sharing_backend.model.enums.CarStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "cars", schema = "carsharing-schema")
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String model;

    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String stateNumber;

    @NotBlank
    private String type;

    @NotNull
    @Positive
    private double price;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private Double latitude;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private Double longitude;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = CarStatus.AVAILABLE;
        }
        roundCoordinates();
    }

    @PreUpdate
    public void preUpdate() {
        roundCoordinates();
    }

    private void roundCoordinates() {
        if (latitude != null) {
            latitude = round(latitude, 5);
        }
        if (longitude != null) {
            longitude = round(longitude, 5);
        }
    }

    private double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        return (double) Math.round(value * factor) / factor;
    }
}

