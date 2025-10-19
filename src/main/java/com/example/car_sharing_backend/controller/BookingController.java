package com.example.car_sharing_backend.controller;

import com.example.car_sharing_backend.mappers.BookingMapper;


import com.example.car_sharing_backend.model.dto.BookingRequestDTO;

import com.example.car_sharing_backend.model.dto.BookingResponseDTO;
import com.example.car_sharing_backend.model.entity.Booking;
import com.example.car_sharing_backend.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;


    @PostMapping
    public ResponseEntity<BookingResponseDTO> saveBooking(@RequestBody BookingRequestDTO request) {
        Booking booking = bookingService.createBooking(request);
        log.info("booking created: {}", booking);
        BookingResponseDTO dto = bookingMapper.toDto(booking);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getBookings() {
        List<Booking> bookings = bookingService.getBookings();
        List<BookingResponseDTO> bookingsDto = bookings.stream()
                .map(bookingMapper::toDto).toList();
        log.debug("Found {} bookings", bookingsDto.size());
        return ResponseEntity.ok(bookingsDto);
    }


}
