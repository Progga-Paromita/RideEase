package com.rideease.service;

import com.rideease.entity.User;
import com.rideease.enums.UserStatus;
import com.rideease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User u = users.findByEmail(email.toLowerCase())
            .orElseThrow(() -> new UsernameNotFoundException("No user with this email"));
        var authorities = u.getRoles().stream()
            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name())).toList();
        return org.springframework.security.core.userdetails.User.builder()
            .username(u.getEmail())
            .password(u.getPassword())
            .authorities(authorities)
            .disabled(u.getStatus() == UserStatus.SUSPENDED)
            .build();
    }
}
