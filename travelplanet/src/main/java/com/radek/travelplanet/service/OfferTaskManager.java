package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class OfferTaskManager implements TaskManager, AutoCloseable {

    private static final int INITIAL_DELAY = 500;
    private final Map<String, ScheduledFuture<?>> offerTasks;
    private final ScheduledExecutorService executorService;
    private final OfferTaskFactory offerTaskFactory;

    public OfferTaskManager(ScheduledExecutorService executorService, OfferTaskFactory offerTaskFactory, Map<String, ScheduledFuture<?>> offerTasks) {
        this.executorService = executorService;
        this.offerTaskFactory = offerTaskFactory;
        this.offerTasks = offerTasks;
    }

    @Override
    public void scheduleTask(Offer offer) {
        Task offerTask = offerTaskFactory.createTask(offer);

        ScheduledFuture<?> offerTaskFuture = executorService.scheduleAtFixedRate(offerTask, INITIAL_DELAY, offerTask.getFrequency(), offerTask.getTimeUnit());
        offerTasks.put(offer.getId(), offerTaskFuture);
    }

    @Override
    public void cancelTask(Offer offer) {
        offerTasks.get(offer.getId()).cancel(false);
    }

    @Override
    public void close() {
        executorService.shutdownNow();
    }
}
