package com.example.car_sharing_backend.model.dto.response;

import com.example.car_sharing_backend.model.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponseDTO {
    private UUID id;
    private UUID userId;
    private UUID carId;
    private String carModel;
    private Double generalPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
}
