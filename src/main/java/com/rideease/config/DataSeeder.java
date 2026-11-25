package com.rideease.config;

import com.rideease.entity.Role;
import com.rideease.entity.User;
import com.rideease.enums.RoleName;
import com.rideease.repository.RoleRepository;
import com.rideease.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final RoleRepository roles;
    private final UserRepository users;
    private final PasswordEncoder encoder;

    @Value("${app.admin-email}")
    private String adminEmail;

    @Value("${app.admin-password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        for (RoleName name : RoleName.values()) {
            if (roles.findByName(name).isEmpty()) {
                Role r = new Role();
                r.setName(name);
                roles.save(r);
            }
        }
        if (!users.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setName("Administrator");
            admin.setEmail(adminEmail);
            admin.setPassword(encoder.encode(adminPassword));
            admin.getRoles().add(roles.findByName(RoleName.ADMIN).orElseThrow());
            users.save(admin);
        }
    }
}
