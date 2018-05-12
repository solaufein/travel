package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class OfferTask implements Task {

    private static Logger LOGGER = LoggerFactory.getLogger(OfferTask.class);

    private final int taskId;
    private final TaskStatus taskStatus;
    private final Offer offer;

    public OfferTask(int taskId, TaskStatus taskStatus, Offer offer) {
        this.taskId = taskId;
        this.taskStatus = taskStatus;
        this.offer = offer;
    }

    @Override
    public void run() {
        LOGGER.info("Offer run: {} ({})", offer.getName(), offer.getLink());
    }

    @Override
    public int getId() {
        return taskId;
    }

    @Override
    public long getFrequency() {
        return Long.parseLong(offer.getFrequency());
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
