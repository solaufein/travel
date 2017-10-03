package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskManager implements TaskManager, AutoCloseable {

    private final Timer timer;
    private final Map<String, TimerTask> offerTasks;

    public TimerTaskManager(Timer timer, Map<String, TimerTask> offerTasks) {
        this.offerTasks = offerTasks;
        this.timer = timer;
    }

    @Override
    public void scheduleTask(Offer offer) {
        TimerTask offerTask = new OfferTask(offer);
        offerTasks.put(offer.getId(), offerTask);
        long frequency = getFrequencyInMilis(offer);

        timer.scheduleAtFixedRate(offerTask, 500, frequency * 1000);
    }

    @Override
    public void cancelTask(Offer offer) {
        offerTasks.get(offer.getId()).cancel();
    }

    @Override
    public void close() {
        timer.cancel();
    }

    private long getFrequencyInMilis(Offer offer) {
        return Long.parseLong(offer.getFrequency());
    }
}
