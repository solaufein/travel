package com.radek.travelplanet.config;

import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.OfferService;
import com.radek.travelplanet.service.OfferServiceImpl;
import com.radek.travelplanet.service.OfferTaskFactory;
import com.radek.travelplanet.service.OfferTaskManager;
import com.radek.travelplanet.service.notify.NotificationService;
import com.radek.travelplanet.service.notify.OfferNotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.Executors;

@Configuration
@PropertySources({@PropertySource(value = "classpath:override.properties", ignoreResourceNotFound = true)})
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

    @Bean
    public NotificationService offerNotifyService(JavaMailSender mailSender) {
        return new OfferNotificationService(mailSender);
    }
}
