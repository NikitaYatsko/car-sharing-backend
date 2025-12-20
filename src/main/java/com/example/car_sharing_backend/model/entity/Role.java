package com.example.car_sharing_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
@Entity
@Table(name = "roles", schema = "carsharing-schema")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Column(unique = true, name = "name")
    private String name;
}
