package com.radek.travelplanet.service;

import com.radek.travelplanet.controller.OfferRequest;
import com.radek.travelplanet.controller.model.OfferDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OfferService {

    Page<OfferDTO> getAllOffers(String userEmail, Pageable pageable);

    long watchNew(OfferRequest offerRequest, String userEmail);

    void watchAll();

    void stopWatching(Long taskId);

    void startWatching(Long taskId);

    void removeFromWatched(Long taskId);
}
