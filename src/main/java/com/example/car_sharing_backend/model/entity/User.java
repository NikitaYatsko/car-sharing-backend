package com.example.car_sharing_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name = "users", schema = "carsharing-schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "Name can't be empty")
    @Column(name = "nickname", nullable = false)
    private String username;

    @NotBlank(message = "Firstname can't be empty")
    @Column(name = "firstname", nullable = false)
    private String firstName;

    @NotBlank(message = "Lastname can't be empty")
    @Column(name = "lastname", nullable = false)
    private String lastName;

    @NotBlank(message = "email can't be empty")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is necessary")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private DrivingLicense drivingLicense;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            schema = "carsharing-schema",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}

