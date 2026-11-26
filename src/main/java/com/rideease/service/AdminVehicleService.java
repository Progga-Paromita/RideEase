package com.rideease.service;

import com.rideease.dto.VehicleResponse;
import com.rideease.entity.Vehicle;
import com.rideease.enums.ApprovalStatus;
import com.rideease.exception.ResourceNotFoundException;
import com.rideease.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminVehicleService {
    private final VehicleRepository vehicles;

    public List<VehicleResponse> all() {
        return vehicles.findAll().stream().map(VehicleResponse::from).toList();
    }

    public List<VehicleResponse> byStatus(ApprovalStatus status) {
        return vehicles.findByApprovalStatus(status).stream().map(VehicleResponse::from).toList();
    }

    public VehicleResponse get(Long id) {
        return VehicleResponse.from(find(id));
    }

    public VehicleResponse setApproval(Long id, ApprovalStatus status) {
        Vehicle vehicle = find(id);
        vehicle.setApprovalStatus(status);
        return VehicleResponse.from(vehicles.save(vehicle));
    }

    private Vehicle find(Long id) {
        return vehicles.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Vehicle " + id + " not found"));
    }
}
