package com.radek.travelplanet.service.task;

import com.radek.travelplanet.exception.OfferException;
import com.radek.travelplanet.service.strategy.PriceStrategy;
import com.radek.travelplanet.service.task.listener.OnFailureListener;
import com.radek.travelplanet.service.task.listener.OnSuccessListener;
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
            long id = taskData.getId();
            String link = taskData.getLink();
            String price = priceStrategy.getPrice(link);
            String name = taskData.getName();

            log.info("Offer name: {}, price: {}. link: {}.", name, price, link);

            notifySuccess(price, id, link, name);
        } catch (Exception ex) {
            log.error("Exception occurred: {}", ex.getMessage());
            notifyFailure(ex);

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

    private void notifyFailure(Exception ex) {
        onFailureListeners.forEach(l -> l.onFailure(this, ex));
    }

    private void notifySuccess(String price, long id, String link, String name) {
        onSuccessListeners.forEach(l -> l.onSuccess(price, id, link, name));
    }
}
