package com.rideease.service;

import com.rideease.dto.RegisterRequest;
import com.rideease.dto.UserResponse;
import com.rideease.entity.User;
import com.rideease.exception.BadRequestException;
import com.rideease.factory.UserFactory;
import com.rideease.repository.RoleRepository;
import com.rideease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrar {
    private final UserRepository users;
    private final RoleRepository roles;
    private final PasswordEncoder encoder;

    public UserResponse register(UserFactory factory, RegisterRequest req) {
        if (users.existsByEmail(req.email().toLowerCase()))
            throw new BadRequestException("Email already in use");
        User user = factory.createUser(req);                      // Factory Method
        user.setPassword(encoder.encode(req.password()));         // BCrypt
        user.getRoles().add(roles.findByName(factory.role()).orElseThrow());
        return UserResponse.from(users.save(user));
    }
}
