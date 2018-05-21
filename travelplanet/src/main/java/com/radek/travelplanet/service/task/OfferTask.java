package com.radek.travelplanet.service.task;

import com.radek.travelplanet.exception.OfferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class OfferTask implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferTask.class);

    private final String taskId;
    private final TaskStatus taskStatus;
    private final TaskCommand taskCommand;
    private String frequency;
    private long initialDelay;

    public OfferTask(String taskId, TaskStatus taskStatus, String frequency, long initialDelay, TaskCommand taskCommand) {
        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.frequency = frequency;
        this.initialDelay = initialDelay;
        this.taskCommand = taskCommand;
    }

    @Override
    public void run() {
        try {
            taskCommand.execute();      //todo: add listeners to notify on change (need to change method execute() to return param?)
        } catch (Exception ex) {
            //todo: add listeners to update status of db Offer and inMemory TaskInfo?

            LOGGER.error("Exception occured: {}", ex.getMessage());
            throw new OfferException("Exception occured: ", ex);
        }
    }

    @Override
    public String getId() {
        return taskId;
    }

    @Override
    public long getFrequency() {
        return Long.parseLong(frequency);
    }

    @Override
    public long getInitialDelay() {
        return initialDelay;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }

    @Override
    public TaskStatus getStatus() {
        return taskStatus;
    }
}
