package com.radek.travelplanet.config;

import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.*;
import com.radek.travelplanet.service.notify.MailNotificationService;
import com.radek.travelplanet.service.notify.NotificationService;
import com.radek.travelplanet.service.parser.ParserFactory;
import com.radek.travelplanet.service.task.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.concurrent.Executors;

@Configuration
@PropertySources({@PropertySource(value = "classpath:override.properties", ignoreResourceNotFound = true)})
@EnableAsync
@EnableScheduling
public class AppConfig {

    @Bean
    public OfferService offerService(OfferTaskFactory offerTaskFactory, TaskManager taskManager, OfferRepository offerRepository) {
        return new OfferServiceImpl(offerTaskFactory, taskManager, offerRepository);
    }

    @Bean
    public OfferSite travelplanetOfferSite(ParserFactory parserFactory) {
        return new TravelplanetOfferSite(parserFactory);
    }

    @Bean
    public OfferSite itakaOfferSite(ParserFactory parserFactory) {
        return new ItakaOfferSite(parserFactory);
    }

    @Bean
    public ParserFactory parserFactory() {
        return new ParserFactory();
    }

    @Bean
    public OfferSiteResolver offerSiteResolver(List<OfferSite> offerSites) {
        return new OfferSiteResolver(offerSites);
    }

    @Bean
    public OfferTaskFactory offerTaskFactory(OfferSiteResolver offerSiteResolver) {
        return new OfferTaskFactory(offerSiteResolver);
    }

    @Bean
    public TaskManager taskManager(TaskRepository taskRepository, TaskScheduler taskScheduler) {
        return new TaskManagerImpl(taskRepository, taskScheduler);
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
    public TaskScheduler taskRunner() {
        return new SimpleFixedTaskScheduler(Executors.newScheduledThreadPool(4));
    }
}
