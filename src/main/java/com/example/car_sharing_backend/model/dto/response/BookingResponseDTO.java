package com.example.car_sharing_backend.model.dto.response;

import com.example.car_sharing_backend.model.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private Long id;
    private Long userId;
    private Long carId;
    private String carModel;
    private Double generalPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
}
