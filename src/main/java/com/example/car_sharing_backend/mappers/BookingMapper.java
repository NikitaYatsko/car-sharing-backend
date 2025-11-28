package com.example.car_sharing_backend.mappers;


import com.example.car_sharing_backend.model.dto.BookingResponseDTO;
import com.example.car_sharing_backend.model.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    default BookingResponseDTO toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getUser().getId());
        dto.setCarId(booking.getCar().getId());
        dto.setCarModel(booking.getCar().getModel());
        dto.setGeneralPrice(booking.getGeneralPrice());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setStatus(booking.getStatus());

        return dto;
    }
}