package com.radek.travelplanet.config;

import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.OfferService;
import com.radek.travelplanet.service.OfferServiceImpl;
import com.radek.travelplanet.service.notify.MailNotificationService;
import com.radek.travelplanet.service.notify.NotificationService;
import com.radek.travelplanet.service.task.*;
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
    public OfferService offerService(OfferTaskFactory offerTaskFactory, TaskManager taskManager, OfferRepository offerRepository) {
        return new OfferServiceImpl(offerTaskFactory, taskManager, offerRepository);
    }

    @Bean
    public OfferTaskFactory offerTaskFactory() {
        return new OfferTaskFactory();
    }

    @Bean
    public TaskManager taskManager(TaskRepository taskRepository, TaskRunner taskRunner) {
        return new TaskManagerImpl(taskRepository, taskRunner);
    }

    @Bean
    public NotificationService notificationService(JavaMailSender mailSender) {
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
