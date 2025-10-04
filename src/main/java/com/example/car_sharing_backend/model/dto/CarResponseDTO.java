package com.example.car_sharing_backend.model.dto;

import lombok.Data;

@Data
public class CarResponseDTO {
    private String model;
    private String stateNumber;
    private String type;
    private Double price;
    private String status;
    private Double latitude;
    private Double longitude;
}
