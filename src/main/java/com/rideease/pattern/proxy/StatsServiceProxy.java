package com.rideease.pattern.proxy;

import com.rideease.dto.PlatformStatsResponse;
import com.rideease.dto.RideStatsResponse;
import com.rideease.dto.UserStatsResponse;
import com.rideease.dto.VehicleStatsResponse;
import com.rideease.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@Primary
public class StatsServiceProxy implements StatsService {
    private static final Logger log = LoggerFactory.getLogger(StatsServiceProxy.class);
    private final StatsService delegate;

    public StatsServiceProxy(@Qualifier("statsServiceImpl") StatsService delegate) {
        this.delegate = delegate;
    }

    @Override
    public UserStatsResponse userMetrics() {
        return timed("userMetrics", delegate::userMetrics);
    }

    @Override
    public VehicleStatsResponse vehicleMetrics() {
        return timed("vehicleMetrics", delegate::vehicleMetrics);
    }

    @Override
    public RideStatsResponse rideMetrics() {
        return timed("rideMetrics", delegate::rideMetrics);
    }

    @Override
    public PlatformStatsResponse platformMetrics() {
        return timed("platformMetrics", delegate::platformMetrics);
    }

    private <T> T timed(String operation, Supplier<T> invocation) {
        long start = System.nanoTime();
        try {
            return invocation.get();
        } finally {
            log.info("StatsService {} completed in {} ns", operation, System.nanoTime() - start);
        }
    }
}
