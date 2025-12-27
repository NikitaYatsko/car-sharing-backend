package com.example.car_sharing_backend.repository;

import com.example.car_sharing_backend.model.entity.Booking;

import com.example.car_sharing_backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("SELECT b FROM Booking b JOIN FETCH b.car WHERE b.user = :user")
    List<Booking> findByUser(User user);
}
