package com.radek.travelplanet.service.task;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SimpleFixedTaskScheduler implements TaskScheduler {

    private final ScheduledExecutorService executorService;

    public SimpleFixedTaskScheduler(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public TaskInfo execute(Task task) {
        ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(task, task.getInitialDelay(), task.getFrequency(), task.getTimeUnit());
        return new TaskInfoImpl(task, scheduledFuture);
    }

    @Override
    public void close() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
