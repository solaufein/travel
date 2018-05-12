package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

import java.util.concurrent.atomic.AtomicInteger;

public class OfferTaskFactory {

    private final AtomicInteger taskId = new AtomicInteger(1);

    public Task createTask(Offer offer) {
        int id = taskId.incrementAndGet();
        return new OfferTask(id, TaskStatus.SUBMITTED, offer);
    }
}
