package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

public interface TaskManager {

    void scheduleTask(Offer offer);

    void cancelTask(Offer offer);
}
