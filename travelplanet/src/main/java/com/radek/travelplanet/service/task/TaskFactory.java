package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.service.PriceStrategy;
import com.radek.travelplanet.service.PriceStrategyRegistry;

import java.util.List;

public class TaskFactory {

    private final PriceStrategyRegistry priceStrategyRegistry;
    private final List<OnFailureListener> onFailureListener;

    public TaskFactory(PriceStrategyRegistry priceStrategyRegistry, List<OnFailureListener> onFailureListener) {
        this.priceStrategyRegistry = priceStrategyRegistry;
        this.onFailureListener = onFailureListener;
    }

    public Task createTask(Offer offer) {
        String offerLink = offer.getLink();
        TaskData taskData = new TaskData(offer.getId(), offerLink, offer.getFrequency(), 3L, offer.getName());
        PriceStrategy priceStrategy = priceStrategyRegistry.resolveStrategy(offerLink);
        OfferTask task = new OfferTask(taskData, priceStrategy);

        //todo: register listeners for task, here or in Manager?
//        task.register(successListener);
        onFailureListener.forEach(task::register);

        return task;
    }
}
