package com.radek.travelplanet.service.task;

import com.radek.travelplanet.exception.OfferException;
import com.radek.travelplanet.service.PriceStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class OfferTask implements Task, ListenableTask {

    private final List<OnSuccessListener> onSuccessListeners = new ArrayList<>();
    private final List<OnFailureListener> onFailureListeners = new ArrayList<>();
    private final TaskData taskData;
    private final PriceStrategy priceStrategy;
    private TaskStatus taskStatus = TaskStatus.SUBMITTED;

    public OfferTask(TaskData taskData, PriceStrategy priceStrategy) {
        this.taskData = taskData;
        this.priceStrategy = priceStrategy;
    }

    @Override
    public void run() {
        try {
            String link = taskData.getLink();
            String price = priceStrategy.getPrice(link);

            log.info("Offer name: {}, price: {}. link: {}.", taskData.getName(), price, link);

            //todo: check price change and send mail
            notifySuccess();
        } catch (Exception ex) {
            //todo: update status of db Offer and inMemory TaskInfo?
            log.error("Exception occurred: {}", ex.getMessage());
            notifyFailure();

            throw new OfferException("Exception occurred: ", ex);
        }
    }

    @Override
    public Long getId() {
        return taskData.getId();
    }

    @Override
    public long getFrequency() {
        return Long.parseLong(taskData.getFrequency());
    }

    @Override
    public long getInitialDelay() {
        return taskData.getInitialDelay();
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
