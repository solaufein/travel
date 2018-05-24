package com.radek.travelplanet.service.task;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.service.OfferSite;
import com.radek.travelplanet.service.OfferSiteResolver;

public class OfferTaskFactory {

    private final OfferSiteResolver offerSiteResolver;

    public OfferTaskFactory(OfferSiteResolver offerSiteResolver) {
        this.offerSiteResolver = offerSiteResolver;
    }

    public Task createTask(Offer offer) {
        String offerLink = offer.getLink();
        OfferSite offerSite = offerSiteResolver.resolveOfferSite(offerLink);

        OfferTask offerTask = new OfferTask(offer, offerSite);
        offerTask.setInitialDelay(3L);
        //todo: register listeners for offerTask

        return offerTask;
    }
}
