package com.radek.travelplanet.service.task;

import com.radek.travelplanet.exception.OfferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class OfferTask implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferTask.class);

    private final int taskId;
    private final TaskStatus taskStatus;
    private final TaskCommand taskCommand;
    private String frequency;

    public OfferTask(int taskId, TaskStatus taskStatus, String frequency, TaskCommand taskCommand) {
        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.frequency = frequency;
        this.taskCommand = taskCommand;
    }

    @Override
    public void run() {
        try {
            taskCommand.execute();
        } catch (Exception ex) {
            LOGGER.error("Exception occured: {}", ex.getMessage());
            throw new OfferException("Exception occured: ", ex);
        }
    }

    @Override
    public int getId() {
        return taskId;
    }

    @Override
    public long getFrequency() {
        return Long.parseLong(frequency);
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
