package com.example.car_sharing_backend.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingRequestDTO {
    private UUID carId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
