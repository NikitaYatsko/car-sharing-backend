package com.example.car_sharing_backend.model.dto.request;

import lombok.Data;

@Data
public class UpdateCarDto {
    private String status;
    private Double latitude;
    private Double longitude;
    private String stateNumber;
}
