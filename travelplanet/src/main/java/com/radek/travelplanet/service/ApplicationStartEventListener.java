package com.radek.travelplanet.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
public class ApplicationStartEventListener {

    private final OfferService offerService;

    public ApplicationStartEventListener(OfferService offerService) {
        this.offerService = offerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startWatchingOffers() {
        log.info("Start watching offers...");
        offerService.watchAll();
    }
}
