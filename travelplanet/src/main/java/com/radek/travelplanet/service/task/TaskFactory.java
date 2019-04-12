package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.service.PriceStrategy;
import com.radek.travelplanet.service.PriceStrategyRegistry;

public class TaskFactory {

    private final PriceStrategyRegistry priceStrategyRegistry;

    public TaskFactory(PriceStrategyRegistry priceStrategyRegistry) {
        this.priceStrategyRegistry = priceStrategyRegistry;
    }

    public Task createTask(Offer offer) {
        String offerLink = offer.getLink();
        TaskData taskData = new TaskData(offer.getId(), offerLink, offer.getFrequency(), 3L, offer.getName());

        PriceStrategy priceStrategy = priceStrategyRegistry.resolveStrategy(offerLink);

        OfferTask task = new OfferTask(taskData, priceStrategy);
        //todo: register listeners for task, here or in Manager?
//        task.register(successListener);
//        task.register(failureListener);

        return task;
    }
}
