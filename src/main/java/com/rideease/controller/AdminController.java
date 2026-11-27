package com.rideease.controller;

import com.rideease.dto.ApprovalRequest;
import com.rideease.dto.PlatformStatsResponse;
import com.rideease.dto.RideResponse;
import com.rideease.dto.RideStatsResponse;
import com.rideease.dto.UserResponse;
import com.rideease.dto.UserStatsResponse;
import com.rideease.dto.UserStatusRequest;
import com.rideease.dto.VehicleResponse;
import com.rideease.dto.VehicleStatsResponse;
import com.rideease.enums.ApprovalStatus;
import com.rideease.enums.RideStatus;
import com.rideease.service.AdminRideService;
import com.rideease.service.AdminVehicleService;
import com.rideease.service.StatsService;
import com.rideease.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final AdminVehicleService vehicleService;
    private final AdminRideService rideService;
    private final StatsService statsService;

    @GetMapping("/users")
    public List<UserResponse> users() {
        return userService.all();
    }

    @GetMapping("/users/{id}")
    public UserResponse user(@PathVariable Long id) {
        return userService.get(id);
    }

    @PatchMapping("/users/{id}/status")
    public UserResponse setUserStatus(@PathVariable Long id, @Valid @RequestBody UserStatusRequest request) {
        return userService.setStatus(id, request.status());
    }

    @GetMapping("/vehicles")
    public List<VehicleResponse> vehicles() {
        return vehicleService.all();
    }

    @GetMapping("/vehicles/status/{status}")
    public List<VehicleResponse> vehiclesByStatus(@PathVariable ApprovalStatus status) {
        return vehicleService.byStatus(status);
    }

    @GetMapping("/vehicles/{id}")
    public VehicleResponse vehicle(@PathVariable Long id) {
        return vehicleService.get(id);
    }

    @PatchMapping("/vehicles/{id}/approval")
    public VehicleResponse setVehicleApproval(@PathVariable Long id, @Valid @RequestBody ApprovalRequest request) {
        return vehicleService.setApproval(id, request.status());
    }

    @GetMapping("/rides")
    public List<RideResponse> rides() {
        return rideService.all();
    }

    @GetMapping("/rides/status/{status}")
    public List<RideResponse> ridesByStatus(@PathVariable RideStatus status) {
        return rideService.byStatus(status);
    }

    @GetMapping("/rides/{id}")
    public RideResponse ride(@PathVariable Long id) {
        return rideService.get(id);
    }

    @GetMapping("/stats")
    public PlatformStatsResponse platformMetrics() {
        return statsService.platformMetrics();
    }

    @GetMapping("/stats/users")
    public UserStatsResponse userMetrics() {
        return statsService.userMetrics();
    }

    @GetMapping("/stats/vehicles")
    public VehicleStatsResponse vehicleMetrics() {
        return statsService.vehicleMetrics();
    }

    @GetMapping("/stats/rides")
    public RideStatsResponse rideMetrics() {
        return statsService.rideMetrics();
    }
}
