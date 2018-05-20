package com.radek.travelplanet.service;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskRunner implements TaskRunner {

    private static final int INITIAL_DELAY = 5;
    private final ScheduledExecutorService executorService;

    public ScheduledTaskRunner(ScheduledExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public TaskInfo execute(Task task) {
        ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(task, INITIAL_DELAY, task.getFrequency(), task.getTimeUnit());
        return createTaskInfo(scheduledFuture);
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

    private TaskInfo createTaskInfo(Future<?> future) {
        return new TaskInfo() {
            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public void cancel() {
                future.cancel(false);
            }
        };
    }
}
