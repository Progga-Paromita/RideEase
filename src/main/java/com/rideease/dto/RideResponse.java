package com.rideease.dto;

import com.rideease.entity.Ride;
import com.rideease.enums.RideStatus;

public record RideResponse(
    Long id,
    Long passengerId,
    Long driverId,
    String pickup,
    String destination,
    double distanceKm,
    double fare,
    double commission,
    RideStatus status,
    String cancelReason,
    boolean paid
) {
    public static RideResponse from(Ride r) {
        return new RideResponse(
            r.getId(),
            r.getPassenger() == null ? null : r.getPassenger().getId(),
            r.getDriver() == null ? null : r.getDriver().getId(),
            r.getPickup(),
            r.getDestination(),
            r.getDistanceKm(),
            r.getFare(),
            r.getCommission(),
            r.getStatus(),
            r.getCancelReason(),
            r.isPaid()
        );
    }
}
