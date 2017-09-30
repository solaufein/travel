package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Offer;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class OfferTaskManager implements AutoCloseable {

    private final Timer timer;
    private final Map<String, TimerTask> offerTasks;

    private OfferTaskManager() {
        this.offerTasks = new HashMap<>();
        this.timer = new Timer();
    }

    public void addTask(Offer offer) {
//        TimerTask offerTask = new OfferTask(offer);
        TimerTask offerTask = new TimerTask() {
            @Override
            public void run() {
                //todo: offer handling
            }
        };
        offerTasks.put(offer.getId(), offerTask);
        long frequency = Long.parseLong(offer.getFrequency());

        timer.scheduleAtFixedRate(offerTask, 500, frequency * 1000);
    }

    public void removeTask(Offer offer) {
        offerTasks.get(offer.getId()).cancel();
    }

    @Override
    public void close() {
        timer.cancel();
    }
}
