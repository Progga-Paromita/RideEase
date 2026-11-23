package com.rideease.service;

import com.rideease.dto.PlatformStatsResponse;
import com.rideease.dto.RideStatsResponse;
import com.rideease.dto.UserStatsResponse;
import com.rideease.dto.VehicleStatsResponse;
import com.rideease.entity.Ride;
import com.rideease.entity.User;
import com.rideease.entity.Vehicle;
import com.rideease.enums.ApprovalStatus;
import com.rideease.enums.RideStatus;
import com.rideease.enums.RoleName;
import com.rideease.enums.UserStatus;
import com.rideease.repository.RideRepository;
import com.rideease.repository.UserRepository;
import com.rideease.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statsServiceImpl")
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final UserRepository users;
    private final VehicleRepository vehicles;
    private final RideRepository rides;

    @Override
    public UserStatsResponse userMetrics() {
        List<User> allUsers = users.findAll();
        return new UserStatsResponse(
            allUsers.size(),
            countUsersWithRole(allUsers, RoleName.ADMIN),
            countUsersWithRole(allUsers, RoleName.SELLER),
            countUsersWithRole(allUsers, RoleName.BUYER),
            allUsers.stream().filter(user -> user.getStatus() == UserStatus.ACTIVE).count(),
            allUsers.stream().filter(user -> user.getStatus() == UserStatus.SUSPENDED).count()
        );
    }

    @Override
    public VehicleStatsResponse vehicleMetrics() {
        List<Vehicle> allVehicles = vehicles.findAll();
        return new VehicleStatsResponse(
            allVehicles.size(),
            countVehicles(allVehicles, ApprovalStatus.PENDING),
            countVehicles(allVehicles, ApprovalStatus.APPROVED),
            countVehicles(allVehicles, ApprovalStatus.REJECTED)
        );
    }

    @Override
    public RideStatsResponse rideMetrics() {
        List<Ride> allRides = rides.findAll();
        List<Ride> completedRides = allRides.stream()
            .filter(ride -> ride.getStatus() == RideStatus.COMPLETED)
            .toList();
        return new RideStatsResponse(
            allRides.size(),
            countRides(allRides, RideStatus.PENDING),
            countRides(allRides, RideStatus.ACCEPTED),
            countRides(allRides, RideStatus.IN_TRANSIT),
            completedRides.size(),
            countRides(allRides, RideStatus.CANCELLED),
            completedRides.stream().mapToDouble(Ride::getFare).sum(),
            completedRides.stream().mapToDouble(Ride::getCommission).sum()
        );
    }

    @Override
    public PlatformStatsResponse platformMetrics() {
        UserStatsResponse userStats = userMetrics();
        VehicleStatsResponse vehicleStats = vehicleMetrics();
        RideStatsResponse rideStats = rideMetrics();
        return new PlatformStatsResponse(
            userStats.totalUsers(),
            vehicleStats.totalVehicles(),
            rideStats.totalRides(),
            rideStats.completedRides(),
            rideStats.grossFare(),
            rideStats.commission()
        );
    }

    private long countUsersWithRole(List<User> allUsers, RoleName roleName) {
        return allUsers.stream()
            .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName() == roleName))
            .count();
    }

    private long countVehicles(List<Vehicle> allVehicles, ApprovalStatus status) {
        return allVehicles.stream().filter(vehicle -> vehicle.getApprovalStatus() == status).count();
    }

    private long countRides(List<Ride> allRides, RideStatus status) {
        return allRides.stream().filter(ride -> ride.getStatus() == status).count();
    }
}
