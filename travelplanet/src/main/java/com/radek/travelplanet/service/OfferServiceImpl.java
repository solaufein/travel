package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.model.OfferStatus;
import com.radek.travelplanet.repository.OfferRepository;
import com.radek.travelplanet.service.task.Task;
import com.radek.travelplanet.service.task.TaskFactory;
import com.radek.travelplanet.service.task.TaskManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public synchronized long watchNew(Offer offer) {
        Offer savedOffer = offerRepository.save(offer);
        Task task = taskFactory.createTask(savedOffer);
        taskManager.startTask(task);
        return savedOffer.getId();
    }

    @Override
    public synchronized void watchAll() {
        Iterable<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            if (offer.getOfferStatus() == OfferStatus.ACTIVE) {
                Task task = taskFactory.createTask(offer);
                taskManager.startTask(task);
            }
        }
    }

    @Override
    public synchronized void stopWatching(Long taskId) {
        taskManager.cancelTask(taskId);
        offerRepository.findById(taskId).ifPresent(this::setInactive);
    }

    @Override
    public synchronized void startWatching(Long taskId) {
        offerRepository.findById(taskId).ifPresent(offer -> {
            if (offer.getOfferStatus() == OfferStatus.INACTIVE) {
                offer.setOfferStatus(OfferStatus.ACTIVE);
                watchNew(offer);
            } else {
                log.warn("Cannot start watching because status is: {}", offer.getOfferStatus());
            }
        });
    }

    @Override
    public synchronized void removeFromWatched(Long taskId) {
        offerRepository.deleteById(taskId);
        taskManager.cancelTask(taskId);
        taskManager.removeTask(taskId);
    }

    private void setInactive(Offer offer) {
        offer.setOfferStatus(OfferStatus.INACTIVE);
        offerRepository.save(offer);
    }
}
