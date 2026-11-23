package com.rideease.dto;

public record RideStatsResponse(
    long totalRides,
    long pendingRides,
    long acceptedRides,
    long inTransitRides,
    long completedRides,
    long cancelledRides,
    double grossFare,
    double commission
) {}
