package com.rideease.factory;

import com.rideease.dto.RegisterRequest;
import com.rideease.entity.User;
import com.rideease.enums.RoleName;

public interface UserFactory {
    RoleName role();
    User createUser(RegisterRequest req);
}
