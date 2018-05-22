package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;

public class OfferTaskFactory {

    private static final long TASK_INITIAL_DELAY = 3L;

    public Task createTask(Offer offer) {
        String taskId = offer.getId();
        String frequency = offer.getFrequency();
        TaskCommand taskCommand = new OfferTaskCommand(offer);

        OfferTask offerTask = new OfferTask(taskId, frequency, TASK_INITIAL_DELAY, taskCommand);


        return offerTask;
    }
}
