package com.radek.travelplanet.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class OfferTaskManager implements TaskManager, AutoCloseable {

    private static final int INITIAL_DELAY = 500;
    private final Map<Integer, ScheduledFuture<?>> offerTasks = new HashMap<>();
    private final ScheduledExecutorService executorService; //todo: create new class to scheduling

    public OfferTaskManager(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void scheduleTask(Task task) {
        ScheduledFuture<?> offerTaskFuture = executorService.scheduleAtFixedRate(task, INITIAL_DELAY, task.getFrequency(), task.getTimeUnit());
        offerTasks.put(task.getId(), offerTaskFuture);
    }

    @Override
    public void cancelTask(Task task) {
        offerTasks.get(task.getId()).cancel(false);
    }

    @Override
    public void close() {
        executorService.shutdownNow();
    }
}
