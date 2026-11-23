package com.rideease.service;

import com.rideease.dto.VehicleResponse;
import com.rideease.entity.User;
import com.rideease.entity.Vehicle;
import com.rideease.enums.ApprovalStatus;
import com.rideease.exception.ResourceNotFoundException;
import com.rideease.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminVehicleServiceTest {

    @Mock
    private VehicleRepository vehicles;

    @InjectMocks
    private AdminVehicleService service;

    private Vehicle sampleVehicle(Long id, ApprovalStatus status) {
        User driver = new User();
        driver.setId(10L);

        Vehicle v = new Vehicle();
        v.setId(id);
        v.setDriver(driver);
        v.setType("car");
        v.setModel("Toyota Axio");
        v.setPlateNumber("DHA-1234");
        v.setApprovalStatus(status);
        return v;
    }

    @Test
    void setApprovalUpdatesStatusToApproved() {
        Vehicle v = sampleVehicle(1L, ApprovalStatus.PENDING);
        when(vehicles.findById(1L)).thenReturn(Optional.of(v));
        when(vehicles.save(any(Vehicle.class))).thenAnswer(i -> i.getArgument(0));

        VehicleResponse res = service.setApproval(1L, ApprovalStatus.APPROVED);

        assertEquals(ApprovalStatus.APPROVED, res.approvalStatus());
        assertEquals(10L, res.driverId());
    }

    @Test
    void setApprovalUpdatesStatusToRejected() {
        Vehicle v = sampleVehicle(2L, ApprovalStatus.PENDING);
        when(vehicles.findById(2L)).thenReturn(Optional.of(v));
        when(vehicles.save(any(Vehicle.class))).thenAnswer(i -> i.getArgument(0));

        VehicleResponse res = service.setApproval(2L, ApprovalStatus.REJECTED);

        assertEquals(ApprovalStatus.REJECTED, res.approvalStatus());
    }

    @Test
    void setApprovalThrowsWhenVehicleNotFound() {
        when(vehicles.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
            () -> service.setApproval(99L, ApprovalStatus.APPROVED));
    }

    @Test
    void allReturnsMappedVehicles() {
        when(vehicles.findAll()).thenReturn(List.of(sampleVehicle(1L, ApprovalStatus.PENDING)));

        List<VehicleResponse> result = service.all();

        assertEquals(1, result.size());
        assertEquals("DHA-1234", result.get(0).plateNumber());
    }
}
