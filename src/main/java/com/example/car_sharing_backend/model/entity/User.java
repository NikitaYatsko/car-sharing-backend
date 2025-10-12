package com.example.car_sharing_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users", schema = "carsharing-schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "lastname can't be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "email can't be empty")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is necessary")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "roles")
    private String roles = "user";

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

