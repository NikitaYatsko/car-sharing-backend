package com.example.car_sharing_backend.model.dto.request;

import lombok.Data;

@Data

public class NewCarRequest {
    private String model;
    private String stateNumber;
    private String type;
    private Double price;
}
