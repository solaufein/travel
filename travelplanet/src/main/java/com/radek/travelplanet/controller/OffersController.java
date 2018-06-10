package com.radek.travelplanet.controller;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.model.OfferStatus;
import com.radek.travelplanet.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@RepositoryRestController
@RequestMapping("/travel")
public class OffersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OffersController.class);

    private final OfferService offerService;

    @Autowired
    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/offers/watch")
    public ResponseEntity<?> watchOffer(@RequestBody OfferRequest offerRequest) {
        LOGGER.info("Watch Offer request received for: {}", offerRequest.getUrl());

        Offer offer = new Offer();
        offer.setName(offerRequest.getUrl());
        offer.setOfferStatus(OfferStatus.ACTIVE);
        offer.setFrequency("5");
        offer.setOfferDetails(new HashSet<>());
        offer.setLink(offerRequest.getUrl());

        offerService.watch(offer);

        return ResponseEntity.ok().build();
    }

//    CUSTOM POST METHOD: (has drawbacks - no hal ?)

//    @PostMapping("/offers")
//    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
//        LOGGER.info("Custom create offer rest method.");
//        Offer savedOffer = offerRepository.save(offer);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(savedOffer.getId()).toUri();
//
////        return ResponseEntity.created(location).build();
//        return ResponseEntity.ok(savedOffer);
//    }
}
