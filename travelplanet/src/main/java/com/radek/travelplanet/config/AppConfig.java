package com.radek.travelplanet.config;

import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.*;
import com.radek.travelplanet.service.notify.MailNotificationService;
import com.radek.travelplanet.service.notify.NotificationService;
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
    public OfferTaskManager offerTaskManager(TaskRepository taskRepository, TaskRunner taskRunner) {
        return new OfferTaskManager(taskRepository, taskRunner);
    }

    @Bean
    public NotificationService offerNotifyService(JavaMailSender mailSender) {
        return new MailNotificationService(mailSender);
    }

    @Bean
    public TaskRepository taskRepository() {
        return new InMemoryTaskRepository();
    }

    @Bean
    public TaskRunner taskRunner() {
        return new ScheduledTaskRunner(Executors.newScheduledThreadPool(4));
    }
}
