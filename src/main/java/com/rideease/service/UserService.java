package com.rideease.service;

import com.rideease.dto.UserResponse;
import com.rideease.entity.User;
import com.rideease.enums.UserStatus;
import com.rideease.exception.ResourceNotFoundException;
import com.rideease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository users;

    public List<UserResponse> all() {
        return users.findAll().stream().map(UserResponse::from).toList();
    }

    public UserResponse get(Long id) {
        return UserResponse.from(find(id));
    }

    public UserResponse setStatus(Long id, UserStatus status) {
        User user = find(id);
        user.setStatus(status);
        return UserResponse.from(users.save(user));
    }

    private User find(Long id) {
        return users.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
    }
}
