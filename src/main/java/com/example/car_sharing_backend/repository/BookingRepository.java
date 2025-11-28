package com.example.car_sharing_backend.repository;

import com.example.car_sharing_backend.model.entity.Booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}
