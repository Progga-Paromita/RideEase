package com.rideease.dto;

public record UserStatsResponse(
    long totalUsers,
    long admins,
    long drivers,
    long passengers,
    long activeUsers,
    long suspendedUsers
) {}
