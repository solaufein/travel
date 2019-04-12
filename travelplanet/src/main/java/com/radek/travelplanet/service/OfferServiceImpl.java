package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.task.TaskFactory;
import com.radek.travelplanet.service.task.Task;
import com.radek.travelplanet.service.task.TaskManager;

public class OfferServiceImpl implements OfferService {

    private final TaskFactory taskFactory;
    private final TaskManager taskManager;
    private final OfferRepository offerRepository;

    public OfferServiceImpl(TaskFactory taskFactory, TaskManager taskManager, OfferRepository offerRepository) {
        this.taskFactory = taskFactory;
        this.taskManager = taskManager;
        this.offerRepository = offerRepository;
    }

    @Override
    public void watchSingle(Offer offer) {
        Offer savedOffer = offerRepository.save(offer);
        Task task = taskFactory.createTask(savedOffer);
        taskManager.startTask(task);
    }

    @Override
    public void watchAll() {
        Iterable<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            Task task = taskFactory.createTask(offer);
            taskManager.startTask(task);
        }
    }
}
