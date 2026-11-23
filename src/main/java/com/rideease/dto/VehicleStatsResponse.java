package com.rideease.dto;

public record VehicleStatsResponse(
    long totalVehicles,
    long pendingVehicles,
    long approvedVehicles,
    long rejectedVehicles
) {}
