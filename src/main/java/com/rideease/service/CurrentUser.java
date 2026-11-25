package com.rideease.service;

import com.rideease.entity.User;
import com.rideease.enums.RoleName;
import com.rideease.exception.ResourceNotFoundException;
import com.rideease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUser {
    private final UserRepository users;

    public User get() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return users.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public boolean has(User user, RoleName role) {
        return user.getRoles().stream().anyMatch(r -> r.getName() == role);
    }
}
