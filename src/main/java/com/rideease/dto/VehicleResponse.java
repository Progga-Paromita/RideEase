package com.rideease.dto;

import com.rideease.entity.Vehicle;
import com.rideease.enums.ApprovalStatus;

public record VehicleResponse(
    Long id,
    Long driverId,
    String type,
    String model,
    String plateNumber,
    ApprovalStatus approvalStatus
) {
    public static VehicleResponse from(Vehicle v) {
        return new VehicleResponse(
            v.getId(),
            v.getDriver() == null ? null : v.getDriver().getId(),
            v.getType(),
            v.getModel(),
            v.getPlateNumber(),
            v.getApprovalStatus()
        );
    }
}
