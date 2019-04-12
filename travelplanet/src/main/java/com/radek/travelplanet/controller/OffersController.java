package com.radek.travelplanet.controller;

import com.radek.travelplanet.model.Offer;
import com.radek.travelplanet.model.OfferStatus;
import com.radek.travelplanet.model.UserAccount;
import com.radek.travelplanet.repository.UserAccountRepository;
import com.radek.travelplanet.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@RepositoryRestController
@RequestMapping("/travel")
@Slf4j
public class OffersController {

    private final OfferService offerService;
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public OffersController(OfferService offerService, UserAccountRepository userAccountRepository) {
        this.offerService = offerService;
        this.userAccountRepository = userAccountRepository;
    }

    @PostMapping("/offers/watch")
    public ResponseEntity<?> watchOffer(@RequestBody OfferRequest offerRequest) {
        log.info("Watch Offer request received for: {}", offerRequest.getUrl());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountRepository.findByEmail(auth.getName());

        Offer offer = new Offer();
        offer.setName(offerRequest.getUrl());
        offer.setOfferStatus(OfferStatus.ACTIVE);
        offer.setFrequency("2");
        offer.setOfferDetails(new HashSet<>());
        offer.setLink(offerRequest.getUrl());
        offer.setUserAccount(userAccount);

        Offer offer2 = new Offer();
        offer2.setName("https://www.travelplanet.pl/wczasy/egipt/hurghada/hurghada/amc-royal--ex-amc-azur-resort,22042019VITX23176.html?box=super-last-minute");
        offer2.setOfferStatus(OfferStatus.ACTIVE);
        offer2.setFrequency("3");
        offer2.setOfferDetails(new HashSet<>());
        offer2.setLink("https://www.travelplanet.pl/wczasy/egipt/hurghada/hurghada/amc-royal--ex-amc-azur-resort,22042019VITX23176.html?box=super-last-minute");
        offer2.setUserAccount(userAccount);

        //https://www.travelplanet.pl/wczasy/egipt/hurghada/hurghada/amc-royal--ex-amc-azur-resort,22042019VITX23176.html?box=super-last-minute

        long id = offerService.watchSingle(offer);
        offerService.watchSingle(offer2);

        return ResponseEntity.ok(id);
    }
}
