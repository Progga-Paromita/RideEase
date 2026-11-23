package com.rideease.dto;

import com.rideease.entity.User;
import com.rideease.enums.UserStatus;

import java.util.Set;
import java.util.stream.Collectors;

public record UserResponse(
    Long id,
    String name,
    String email,
    UserStatus status,
    Set<String> roles
) {
    public static UserResponse from(User u) {
        return new UserResponse(
            u.getId(),
            u.getName(),
            u.getEmail(),
            u.getStatus(),
            u.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet())
        );
    }
}
