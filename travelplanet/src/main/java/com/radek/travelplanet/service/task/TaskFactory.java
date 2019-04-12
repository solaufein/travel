package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.service.strategy.PriceStrategy;
import com.radek.travelplanet.service.strategy.PriceStrategyRegistry;
import com.radek.travelplanet.service.task.listener.OnFailureListener;
import com.radek.travelplanet.service.task.listener.OnSuccessListener;

import java.util.List;

public class TaskFactory {

    private final PriceStrategyRegistry priceStrategyRegistry;
    private final List<OnFailureListener> onFailureListener;
    private final List<OnSuccessListener> onSuccessListeners;

    public TaskFactory(PriceStrategyRegistry priceStrategyRegistry, List<OnFailureListener> onFailureListener, List<OnSuccessListener> onSuccessListeners) {
        this.priceStrategyRegistry = priceStrategyRegistry;
        this.onFailureListener = onFailureListener;
        this.onSuccessListeners = onSuccessListeners;
    }

    public Task createTask(Offer offer) {
        String offerLink = offer.getLink();
        TaskData taskData = new TaskData(offer.getId(), offerLink, offer.getFrequency(), 3L, offer.getName());
        PriceStrategy priceStrategy = priceStrategyRegistry.resolveStrategy(offerLink);

        OfferTask task = new OfferTask(taskData, priceStrategy);
        onSuccessListeners.forEach(task::register);
        onFailureListener.forEach(task::register);

        return task;
    }
}
