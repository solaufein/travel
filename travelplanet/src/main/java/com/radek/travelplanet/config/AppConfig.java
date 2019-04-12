package com.radek.travelplanet.config;

import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.ApplicationStartEventListener;
import com.radek.travelplanet.service.OfferService;
import com.radek.travelplanet.service.OfferServiceImpl;
import com.radek.travelplanet.service.notify.MailNotificationService;
import com.radek.travelplanet.service.notify.NotificationService;
import com.radek.travelplanet.service.parser.ParserFactory;
import com.radek.travelplanet.service.strategy.ItakaPriceStrategy;
import com.radek.travelplanet.service.strategy.PriceStrategy;
import com.radek.travelplanet.service.strategy.PriceStrategyRegistry;
import com.radek.travelplanet.service.strategy.TravelplanetPriceStrategy;
import com.radek.travelplanet.service.task.*;
import com.radek.travelplanet.service.task.listener.OnFailureListener;
import com.radek.travelplanet.service.task.listener.OnFailureListenerImpl;
import com.radek.travelplanet.service.task.listener.OnSuccessListener;
import com.radek.travelplanet.service.task.listener.OnSuccessListenerImpl;
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
    public TaskFactory taskFactory(PriceStrategyRegistry priceStrategyRegistry, List<OnFailureListener> onFailureListeners, List<OnSuccessListener> onSuccessListeners) {
        return new TaskFactory(priceStrategyRegistry, onFailureListeners, onSuccessListeners);
    }

    @Bean
    public OnFailureListenerImpl onFailureListener(TaskManager taskManager, OfferRepository offerRepository) {
        return new OnFailureListenerImpl(taskManager, offerRepository);
    }

    @Bean
    public OnSuccessListener onSuccessListener(OfferRepository offerRepository, NotificationService notificationService) {
        return new OnSuccessListenerImpl(offerRepository, notificationService);
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

    @Bean
    public ApplicationStartEventListener applicationStartEventListener(OfferService offerService) {
        return new ApplicationStartEventListener(offerService);
    }
}
