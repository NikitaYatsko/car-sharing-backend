package com.example.car_sharing_backend.model.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class CarResponseDTO {
    private UUID id;
    private String model;
    private String stateNumber;
    private String type;
    private Double price;
    private String status;
    private Double latitude;
    private Double longitude;
}
