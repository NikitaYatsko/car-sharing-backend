package com.example.car_sharing_backend.model.dto.request;

import lombok.Data;

@Data
public class CarData{
    private String model;
    private String stateNumber;
    private String type;
    private Double price;
}
