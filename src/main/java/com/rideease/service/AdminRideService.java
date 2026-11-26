package com.rideease.service;

import com.rideease.dto.RideResponse;
import com.rideease.entity.Ride;
import com.rideease.enums.RideStatus;
import com.rideease.exception.ResourceNotFoundException;
import com.rideease.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRideService {
    private final RideRepository rides;

    public List<RideResponse> all() {
        return rides.findAll().stream().map(RideResponse::from).toList();
    }

    public List<RideResponse> byStatus(RideStatus status) {
        return rides.findByStatus(status).stream().map(RideResponse::from).toList();
    }

    public RideResponse get(Long id) {
        return RideResponse.from(find(id));
    }

    private Ride find(Long id) {
        return rides.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Ride " + id + " not found"));
    }
}
