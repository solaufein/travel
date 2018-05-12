package com.radek.travelplanet.config;

import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.OfferService;
import com.radek.travelplanet.service.OfferServiceImpl;
import com.radek.travelplanet.service.OfferTaskFactory;
import com.radek.travelplanet.service.OfferTaskManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public OfferService offerService(OfferTaskFactory offerTaskFactory, OfferTaskManager offerTaskManager, OfferRepository offerRepository) {
        return new OfferServiceImpl(offerTaskFactory, offerTaskManager, offerRepository);
    }

    @Bean
    public OfferTaskFactory offerTaskFactory() {
        return new OfferTaskFactory();
    }

    @Bean
    public OfferTaskManager offerTaskManager() {
        return new OfferTaskManager(Executors.newSingleThreadScheduledExecutor());
    }
}
