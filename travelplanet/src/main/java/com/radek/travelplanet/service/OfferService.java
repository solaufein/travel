package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

public interface OfferService {

    long watchNew(Offer offer);

    void watchAll();

    void stopWatching(Long taskId);

    void startWatching(Long taskId);

    void removeFromWatched(Long taskId);
}
