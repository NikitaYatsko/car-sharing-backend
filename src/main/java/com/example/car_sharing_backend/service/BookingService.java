package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.dto.BookingRequestDTO;
import com.example.car_sharing_backend.model.dto.BookingResponseDTO;
import com.example.car_sharing_backend.model.entity.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequestDTO requestDto);
    List<Booking> getBookings();
}
