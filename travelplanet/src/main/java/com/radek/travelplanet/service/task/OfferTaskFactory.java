package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;

public class OfferTaskFactory {

    private static final long TASK_INITIAL_DELAY = 3L;

    //todo: add listeners to notify on change
    public Task createTask(Offer offer) {
        String taskId = offer.getId();
        String frequency = offer.getFrequency();
        TaskCommand taskCommand = new OfferTaskCommand(offer);

        return new OfferTask(taskId, TaskStatus.SUBMITTED, frequency, TASK_INITIAL_DELAY, taskCommand);
    }
}
