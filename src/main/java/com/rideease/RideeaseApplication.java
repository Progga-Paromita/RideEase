package com.rideease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RideeaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideeaseApplication.class, args);
    }
}
