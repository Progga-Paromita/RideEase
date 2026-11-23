package com.rideease.repository;

import com.rideease.entity.Vehicle;
import com.rideease.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDriverId(Long driverId);
    List<Vehicle> findByApprovalStatus(ApprovalStatus approvalStatus);
    Optional<Vehicle> findFirstByDriverIdAndApprovalStatus(Long driverId, ApprovalStatus approvalStatus);
    boolean existsByPlateNumber(String plateNumber);
}
