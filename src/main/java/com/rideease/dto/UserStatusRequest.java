package com.rideease.dto;

import com.rideease.enums.UserStatus;
import jakarta.validation.constraints.NotNull;

public record UserStatusRequest(
    @NotNull UserStatus status
) {}
