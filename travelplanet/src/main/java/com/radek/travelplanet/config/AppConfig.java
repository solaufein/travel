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
    public OfferService offerService(TaskFactory taskFactory, TaskManager taskManager, OfferRepository offerRepository) {
        return new OfferServiceImpl(taskFactory, taskManager, offerRepository);
    }

    @Bean
    public PriceStrategy travelplanetPriceStrategy(ParserFactory parserFactory) {
        return new TravelplanetPriceStrategy(parserFactory);
    }

    @Bean
    public PriceStrategy itakaPriceStrategy(ParserFactory parserFactory) {
        return new ItakaPriceStrategy(parserFactory);
    }

    @Bean
    public ParserFactory parserFactory() {
        return new ParserFactory();
    }

    @Bean
    public PriceStrategyRegistry priceStrategyRegistry(List<PriceStrategy> sitePriceStrategies) {
        return new PriceStrategyRegistry(sitePriceStrategies);
    }

    @Bean
    public TaskFactory taskFactory(PriceStrategyRegistry priceStrategyRegistry) {
        return new TaskFactory(priceStrategyRegistry);
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
