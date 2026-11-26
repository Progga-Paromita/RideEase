package com.rideease.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "fare")
public class FareConfig {
    private double baseFare = 50;
    private double perKm = 20;
    private double commissionPercent = 20;
}
