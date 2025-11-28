package com.example.car_sharing_backend.service.implementation;

import com.example.car_sharing_backend.exception.CarNotFoundException;
import com.example.car_sharing_backend.exception.UserNotFoundException;
import com.example.car_sharing_backend.model.dto.request.BookingRequestDTO;
import com.example.car_sharing_backend.model.entity.Booking;
import com.example.car_sharing_backend.model.entity.Car;
import com.example.car_sharing_backend.model.entity.User;
import com.example.car_sharing_backend.model.enums.BookingStatus;
import com.example.car_sharing_backend.model.enums.CarStatus;
import com.example.car_sharing_backend.repository.BookingRepository;
import com.example.car_sharing_backend.repository.CarRepository;
import com.example.car_sharing_backend.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final CarRepository carRepository;


    @Transactional
    @Override
    public Booking createBooking(BookingRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Car not found"));
        if (car.getStatus() == CarStatus.RENTED) {
            throw new RuntimeException("Car is already booked");
        }
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new RuntimeException("Start date must be before end date");
        }

        long hours = Duration.between(dto.getStartDate(), dto.getEndDate()).toHours();
        Double totalPrice = car.getPrice() * hours;

        Booking bookingToSave = new Booking();
        bookingToSave.setUser(user);
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
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }


}
