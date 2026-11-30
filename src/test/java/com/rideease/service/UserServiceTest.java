package com.rideease.service;

import com.rideease.dto.UserResponse;
import com.rideease.entity.User;
import com.rideease.enums.UserStatus;
import com.rideease.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldUpdateUserStatus() {
        User user = new User();
        user.setId(1L);
        user.setStatus(UserStatus.ACTIVE);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserResponse response = userService.setStatus(1L, UserStatus.SUSPENDED);

        assertEquals(UserStatus.SUSPENDED, response.status());
        verify(userRepository).save(user);
    }
}
