package com.rideease.pattern.proxy;

import com.rideease.dto.PlatformStatsResponse;
import com.rideease.dto.RideStatsResponse;
import com.rideease.dto.UserStatsResponse;
import com.rideease.dto.VehicleStatsResponse;
import com.rideease.service.StatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatsServiceProxyTest {
    @Mock
    private StatsService delegate;

    @Test
    void proxyDelegatesDashboardMetricsWithoutChangingResults() {
        UserStatsResponse users = new UserStatsResponse(1, 0, 1, 0, 1, 0);
        VehicleStatsResponse vehicles = new VehicleStatsResponse(1, 0, 1, 0);
        RideStatsResponse rides = new RideStatsResponse(1, 0, 0, 0, 1, 0, 250, 50);
        PlatformStatsResponse platform = new PlatformStatsResponse(1, 1, 1, 1, 250, 50);
        when(delegate.userMetrics()).thenReturn(users);
        when(delegate.vehicleMetrics()).thenReturn(vehicles);
        when(delegate.rideMetrics()).thenReturn(rides);
        when(delegate.platformMetrics()).thenReturn(platform);
        StatsServiceProxy proxy = new StatsServiceProxy(delegate);

        assertSame(users, proxy.userMetrics());
        assertSame(vehicles, proxy.vehicleMetrics());
        assertSame(rides, proxy.rideMetrics());
        assertSame(platform, proxy.platformMetrics());

        verify(delegate).userMetrics();
        verify(delegate).vehicleMetrics();
        verify(delegate).rideMetrics();
        verify(delegate).platformMetrics();
    }
}
