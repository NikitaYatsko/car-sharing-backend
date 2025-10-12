package com.example.car_sharing_backend.model.entity;

import com.example.car_sharing_backend.model.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings", schema = "carsharing-schema")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @JoinColumn(name = "car_id")
    @ManyToOne
    private Car car;
    @Column(name = "start_date")
    @CreationTimestamp
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @NotNull(message = "Booking must have a price!")
    @Column(name = "general_price")
    private Double generalPrice;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
