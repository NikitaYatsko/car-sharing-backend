package com.example.car_sharing_backend.service;

import com.example.car_sharing_backend.model.dto.request.BookingRequestDTO;
import com.example.car_sharing_backend.model.entity.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(BookingRequestDTO requestDto);
    List<Booking> getBookings();
}
