package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;

import java.util.concurrent.atomic.AtomicInteger;

public class OfferTaskFactory {

    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public Task createTask(Offer offer) {
        int taskId = idGenerator.incrementAndGet();
        String frequency = offer.getFrequency();
        TaskCommand taskCommand = new OfferTaskCommand(offer);

        return new OfferTask(taskId, TaskStatus.SUBMITTED, frequency, taskCommand);
    }
}
