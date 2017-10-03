package com.radek.travelplanet.config;

import com.radek.travelplanet.service.OfferTaskFactory;
import com.radek.travelplanet.service.OfferTaskManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.concurrent.Executors;

@Configuration
public class BeanConfig {

    @Bean
    public OfferTaskManager offerTaskManager() {
        return new OfferTaskManager(Executors.newSingleThreadScheduledExecutor(), new OfferTaskFactory(), new HashMap<>());
    }

}
