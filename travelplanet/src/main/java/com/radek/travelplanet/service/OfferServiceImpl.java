package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.repository.OfferRepository;

public class OfferServiceImpl implements OfferService {

    private final OfferTaskFactory offerTaskFactory;
    private final OfferTaskManager offerTaskManager;
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferTaskFactory offerTaskFactory, OfferTaskManager offerTaskManager, OfferRepository offerRepository) {
        this.offerTaskFactory = offerTaskFactory;
        this.offerTaskManager = offerTaskManager;
        this.offerRepository = offerRepository;
    }

    @Override
    public void watch(Offer offer) {
        Offer savedOffer = offerRepository.save(offer);
        Task task = offerTaskFactory.createTask(savedOffer);
        offerTaskManager.startTask(task);
    }
}
