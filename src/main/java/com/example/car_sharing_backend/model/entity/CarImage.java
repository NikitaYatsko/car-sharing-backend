package com.example.car_sharing_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.UUID;
@Data
@Entity
@Table(name = "car_images", schema = "carsharing-schema")
public class CarImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    @Column(name = "image_url")
    private String imageUrl;
}
