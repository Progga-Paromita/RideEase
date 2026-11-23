package com.rideease.dto;

public record PlatformStatsResponse(
    long totalUsers,
    long totalVehicles,
    long totalRides,
    long completedRides,
    double grossFare,
    double commission
) {}
