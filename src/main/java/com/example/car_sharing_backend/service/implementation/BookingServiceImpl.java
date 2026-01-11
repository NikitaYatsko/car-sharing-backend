package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.exception.CarAlreadyRentedException;
import com.example.car_sharing_backend.exception.CarNotFoundException;
import com.example.car_sharing_backend.exception.DrivingLicenseNotFoundException;
import com.example.car_sharing_backend.mappers.BookingMapper;
import com.example.car_sharing_backend.model.dto.request.BookingRequestDTO;
import com.example.car_sharing_backend.model.dto.response.BookingResponseDTO;
import com.example.car_sharing_backend.model.entity.Booking;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.model.enums.BookingStatus;
import com.example.car_sharing_backend.model.enums.CarStatus;
import com.example.car_sharing_backend.model.enums.ErrorMessage;
import com.example.car_sharing_backend.repository.BookingRepository;
import com.example.car_sharing_backend.repository.CarRepository;
import com.example.car_sharing_backend.service.BookingService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CurrentUserService currentUserService;
    private final CarRepository carRepository;
    private final BookingMapper bookingMapper;


    @Transactional
    @Override
    public Booking createBooking(BookingRequestDTO dto) {
        User currentUser = currentUserService.getCurrentUser();

        if (currentUser.getDrivingLicense() == null) {
            throw new DrivingLicenseNotFoundException(ErrorMessage.DRIVING_LICENSE_NOT_FOUND.getMessage());
        }

        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new CarNotFoundException(ErrorMessage.CAR_NOT_FOUND_BY_ID.getMessage()));

        if (car.getStatus() == CarStatus.RENTED) {
            throw new CarAlreadyRentedException(ErrorMessage.CAR_ALREADY_RENTED.getMessage());
        }
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new RuntimeException("Start date must be before end date");
        }

        long hours = Duration.between(dto.getStartDate(), dto.getEndDate()).toHours();
        Double totalPrice = car.getPrice() * hours;

        Booking bookingToSave = new Booking();
        bookingToSave.setUser(currentUser);
        bookingToSave.setCar(car);
        bookingToSave.setStartDate(dto.getStartDate());
        bookingToSave.setEndDate(dto.getEndDate());
        bookingToSave.setGeneralPrice(totalPrice);
        bookingToSave.setStatus(BookingStatus.ACTIVE);

        car.setStatus(CarStatus.RENTED);
        carRepository.save(car);
        return bookingRepository.save(bookingToSave);
    }

    @Override
    public List<BookingResponseDTO> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toDto).toList();
    }

    @Override
    public List<BookingResponseDTO> getBookingsOfCurrentUser() {
        User currentUser = currentUserService.getCurrentUser();
        List<Booking> bookings = bookingRepository.findByUser(currentUser);
        return bookings.stream()
                .map(bookingMapper::toDto)
                .toList();
    }


}
