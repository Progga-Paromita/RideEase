package com.rideease.entity;

import com.rideease.enums.RideStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "rides")
@Getter
@Setter
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private User driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(nullable = false)
    private String pickup;

    @Column(nullable = false)
    private String destination;

    private double pickupLat;
    private double pickupLng;
    private double dropLat;
    private double dropLng;

    @Column(nullable = false)
    private String vehicleType;

    private double distanceKm;
    private double fare;
    private double commission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status = RideStatus.PENDING;

    private String cancelReason;

    private boolean paid = false;

    private Instant createdAt = Instant.now();

    private Instant completedAt;
}
