package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.task.OfferTaskFactory;
import com.radek.travelplanet.service.task.Task;
import com.radek.travelplanet.service.task.TaskManager;

public class OfferServiceImpl implements OfferService {

    private final OfferTaskFactory offerTaskFactory;
    private final TaskManager taskManager;
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferTaskFactory offerTaskFactory, TaskManager taskManager, OfferRepository offerRepository) {
        this.offerTaskFactory = offerTaskFactory;
        this.taskManager = taskManager;
        this.offerRepository = offerRepository;
    }

    @Override
    public void watch(Offer offer) {
        Offer savedOffer = offerRepository.save(offer);
        Task task = offerTaskFactory.createTask(savedOffer);
        taskManager.startTask(task);
    }
}
