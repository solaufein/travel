package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class OfferTask extends TimerTask {

    private static Logger LOGGER = LoggerFactory.getLogger(OfferTask.class);

    private final Offer offer;

    public OfferTask(Offer offer) {
        this.offer = offer;
    }

    @Override
    public void run() {
        LOGGER.info("Offer run: {} ({})", offer.getName(), offer.getLink());
    }
}
