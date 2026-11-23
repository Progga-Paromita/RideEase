package com.rideease.dto;

import com.rideease.enums.ApprovalStatus;
import jakarta.validation.constraints.NotNull;

public record ApprovalRequest(
    @NotNull ApprovalStatus status
) {}
