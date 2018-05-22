package com.radek.travelplanet.service.task;

import com.radek.travelplanet.exception.OfferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OfferTask implements Task, ListenableTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferTask.class);

    private final List<OnSuccessListener> onSuccessListeners = new ArrayList<>();
    private final List<OnFailureListener> onFailureListeners = new ArrayList<>();
    private final String taskId;
    private final TaskCommand taskCommand;
    private final String frequency;
    private final long initialDelay;
    private TaskStatus taskStatus = TaskStatus.SUBMITTED;

    public OfferTask(String taskId, String frequency, long initialDelay, TaskCommand taskCommand) {
        this.taskId = taskId;
        this.frequency = frequency;
        this.initialDelay = initialDelay;
        this.taskCommand = taskCommand;
    }

    @Override
    public void run() {
        try {
            //todo: add listeners to notify on change (need to change method execute() to return param?)
            taskCommand.execute();
            notifySuccess();
        } catch (Exception ex) {
            //todo: add listeners to update status of db Offer and inMemory TaskInfo?
            LOGGER.error("Exception occured: {}", ex.getMessage());
            notifyFailure();

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

    @Override
    public void updateStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public void register(OnSuccessListener listener) {
        this.onSuccessListeners.add(listener);
    }

    @Override
    public void register(OnFailureListener listener) {
        this.onFailureListeners.add(listener);
    }

    private void notifyFailure() {
        onFailureListeners.forEach(l -> l.onFailure(this));
    }

    private void notifySuccess() {
        onSuccessListeners.forEach(l -> l.onSuccess());
    }
}
