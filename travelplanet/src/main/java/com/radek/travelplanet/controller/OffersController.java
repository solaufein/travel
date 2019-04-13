package com.radek.travelplanet.controller;

import com.radek.travelplanet.controller.model.OfferDTO;
import com.radek.travelplanet.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RepositoryRestController
@RequestMapping("/travel")
@Slf4j
public class OffersController {

    private final OfferService offerService;

    @Autowired
    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers")
    public ResponseEntity<?> getAllOffers(Pageable pageable, Principal principal) {
        Page<OfferDTO> offers = offerService.getAllOffers(principal.getName(), pageable);

        return ResponseEntity.ok(offers);
    }

    @PostMapping("/offers")
    public ResponseEntity<?> watchOffer(@RequestBody OfferRequest offerRequest, Principal principal) {
        log.info("Watch Offer request received for: {}", offerRequest.getUrl());
        long id = offerService.watchNew(offerRequest, principal.getName());

        return ResponseEntity.ok(id);
    }
}
