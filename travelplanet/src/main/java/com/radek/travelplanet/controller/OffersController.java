package com.radek.travelplanet.controller;

import com.radek.travelplanet.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PostMapping;

@RepositoryRestController
public class OffersController {

    private static Logger LOGGER = LoggerFactory.getLogger(OffersController.class);

    private final OfferService offerService;

    @Autowired
    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/offers/aaa")
    public String customOffersMethod() {
        LOGGER.info("Custom offers rest method.");

        return "index";
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
