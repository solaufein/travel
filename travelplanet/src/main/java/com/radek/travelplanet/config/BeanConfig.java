package com.radek.travelplanet.config;

import com.radek.travelplanet.service.TimerTaskManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Timer;

@Configuration
public class BeanConfig {

    @Bean
    public TimerTaskManager timerTaskManager() {
        return new TimerTaskManager(new Timer(), new HashMap<>());
    }

}
