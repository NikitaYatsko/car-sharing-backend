package com.example.car_sharing_backend.controller;


import com.example.car_sharing_backend.mappers.BookingMapper;
import com.example.car_sharing_backend.model.dto.request.BookingRequestDTO;

import com.example.car_sharing_backend.model.dto.response.BookingResponseDTO;
import com.example.car_sharing_backend.model.entity.Booking;
import com.example.car_sharing_backend.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<BookingResponseDTO> saveBooking(@RequestBody BookingRequestDTO request) {
        Booking booking = bookingService.createBooking(request);
        log.info("booking created: {}", booking);
        BookingResponseDTO dto = bookingMapper.toDto(booking);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getBookings() {
        List<BookingResponseDTO> responseDTO = bookingService.getBookings();
        log.debug("Found {} bookings", responseDTO.size());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/my")
    public ResponseEntity<List<BookingResponseDTO>> getMyBookings() {
        List<BookingResponseDTO> response = bookingService.getBookingsOfCurrentUser();
        return ResponseEntity.ok(response);
    }


}
