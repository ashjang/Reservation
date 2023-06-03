package com.ashjang.store.config;

import com.ashjang.domain.location.LocationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfig {

    @Bean
    public LocationProvider locationProvider() {
        return new LocationProvider();
    }
}
