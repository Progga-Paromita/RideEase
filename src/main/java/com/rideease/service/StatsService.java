package com.rideease.service;

import com.rideease.dto.PlatformStatsResponse;
import com.rideease.dto.RideStatsResponse;
import com.rideease.dto.UserStatsResponse;
import com.rideease.dto.VehicleStatsResponse;

public interface StatsService {
    UserStatsResponse userMetrics();
    VehicleStatsResponse vehicleMetrics();
    RideStatsResponse rideMetrics();
    PlatformStatsResponse platformMetrics();
}
