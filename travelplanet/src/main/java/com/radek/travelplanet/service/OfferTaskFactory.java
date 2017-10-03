package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

public class OfferTaskFactory {

    public Task createTask(Offer offer) {
        return new OfferTask(offer);
    }
}
